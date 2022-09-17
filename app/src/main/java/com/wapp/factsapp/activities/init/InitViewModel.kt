package com.wapp.factsapp.activities.init



import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.repositories.categoriesDataRepository.CategoriesDataRepo
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import com.wapp.factsapp.data.repositories.questionsDataRepository.QuestionDataRepository
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.models.Question
import com.wapp.factsapp.utils.categoriesList
import com.wapp.factsapp.utils.categoriesNameList
import com.wapp.factsapp.utils.questionsList
import com.wapp.factsapp.workmanager.DailyFactWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit


class InitViewModel @ViewModelInject constructor(private val factsDataRepo: FactsDataRepo,
                                                 private val preferenceProvider: PreferenceProvider,
                                                 private val workManager: WorkManager,
                                                 private val categoriesDataRepo: CategoriesDataRepo,
                                                 private val questionDataRepository: QuestionDataRepository): ViewModel() {


    private val _updateFinished = MutableLiveData<String>()
    val updateFinished: LiveData<String>
        get() = _updateFinished

    companion object{
        const val WITHOUT_UPDATES = "there_is_no_fact_updates"
        const val WITH_UPDATES = "there_is_fact_update"
    }

    init {
        // check if the daily work is already launched, if not launch it
        if(!preferenceProvider.dailyWorkLaunched()){
            launchDailyWork()
            preferenceProvider.setDailyWorkLaunchedTo(true)
        }

        // if picked categories list is empty, init it with all categories name
        if(preferenceProvider.getPickedCategories().isEmpty())
            preferenceProvider.setPickedCategories(categoriesNameList().toSet())

        // Check if it's the first use of the app and make the appropriate updates
        if(preferenceProvider.isFirstUse())
            getAndInsertAllFactsIntoRoom()
        else
            insertNewFactsIntoRoom()

    }

    private fun insertNewFactsIntoRoom(){
        viewModelScope.launch(Dispatchers.IO) {

            //insert new remote questions
            insertNewQuestions()


            val remoteFactIds = mutableListOf<String>()
            val localFactIds = mutableListOf<String>()
            val remoteFacts = factsDataRepo.getAllFactsFromFireStore()
            val localFacts = factsDataRepo.getDailyFacts(false)

            remoteFacts.forEach {
                remoteFactIds.add(it.id)
            }

            localFacts.forEach {
                localFactIds.add(it.id)
            }

            val list = mutableListOf<Fact>()
            if(remoteFacts.size > localFacts.size){
                remoteFactIds.forEach { id ->
                    if(!localFactIds.contains(id))
                        list.add(remoteFacts.filter { it.id == id }[0])
                }
                factsDataRepo.insertAllFactsIntoRoom(list)
                withContext(Dispatchers.Main){
                    _updateFinished.value = WITH_UPDATES
                }
            }
            else
                withContext(Dispatchers.Main){
                    _updateFinished.value = WITHOUT_UPDATES
                }

        }
    }

    private suspend fun insertNewQuestions(){
        val list = mutableListOf<Question>()
        val remoteQuestions = questionDataRepository.getRemoteQuestions()
        val localQuestions = questionDataRepository.getAllQuestions().filter { it.remote }
        if(remoteQuestions.size > localQuestions.size){
            remoteQuestions.forEach {
                if(!localQuestions.contains(it))
                    list.add(it)
            }
            questionDataRepository.insertAllQuestions(list)
        }
    }

    private fun getAndInsertAllFactsIntoRoom(){
        viewModelScope.launch(Dispatchers.IO) {

            //insert question from local list
            questionDataRepository.insertAllQuestions(questionsList())

            factsDataRepo.insertAllFactsIntoRoom(factsDataRepo.getAllFactsFromFireStore())
            categoriesDataRepo.insertAllCategories(categoriesList())
            preferenceProvider.setFirstUseTo(false)
            withContext(Dispatchers.Main){
                _updateFinished.value = WITHOUT_UPDATES
            }
        }
    }



    private fun launchDailyWork(){
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()
        val request = PeriodicWorkRequest
            .Builder(DailyFactWorker::class.java,1,TimeUnit.DAYS)
            .setInitialDelay(1,TimeUnit.DAYS)
            .setConstraints(constraint)
            .build()
        workManager.enqueue(request)
    }
}