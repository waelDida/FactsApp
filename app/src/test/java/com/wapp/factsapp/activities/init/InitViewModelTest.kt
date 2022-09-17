package com.wapp.factsapp.activities.init

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.work.WorkManager
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.data.sources.quiz.FakeQuestionsDataRepo
import com.wapp.factsapp.data.sources.categories.FakeCategoriesRepo
import com.wapp.factsapp.data.sources.fact.FakeFactRepository
import com.wapp.factsapp.models.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class InitViewModelTest{
    private lateinit var initViewModel: InitViewModel
    private lateinit var fakeFactRepository: FakeFactRepository
    private lateinit var fakePreferenceProvider: FakePrefProvider
    private lateinit var fakeCategoriesRepo: FakeCategoriesRepo
    private lateinit var fakeQuestionsDataRepo: FakeQuestionsDataRepo

    private val remoteFactList = listOf(Fact("1",dailyFact = false),Fact("2",dailyFact = false),Fact("3",dailyFact = false))

    private val workManager: WorkManager = Mockito.mock(WorkManager::class.java)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() = runBlockingTest{
        Dispatchers.setMain(Dispatchers.Unconfined)

        fakeFactRepository = FakeFactRepository()
        fakeFactRepository.addFacts(Fact("1",dailyFact = false),Fact("2",dailyFact = false),Fact("3",dailyFact = false),Fact("4",dailyFact = true))
        fakeFactRepository.setRemoteList(remoteFactList)

        fakePreferenceProvider = FakePrefProvider()
        fakeCategoriesRepo = FakeCategoriesRepo()
        fakeQuestionsDataRepo = FakeQuestionsDataRepo()

        fakePreferenceProvider.setFirstUseTo(false)
        initViewModel = InitViewModel(fakeFactRepository,fakePreferenceProvider,workManager,fakeCategoriesRepo,fakeQuestionsDataRepo)


    }

    @Test
    fun`verify that the first use of the app worked with success`() {
        assertThat(fakePreferenceProvider.isFirstUse(),IsEqual(false))
    }

    @Test
    fun`verify that the categories list name  has been stored in pref`(){
        assertThat(fakePreferenceProvider.getPickedCategories().isNotEmpty(),IsEqual(true))
    }

    @Test
    fun`check that the dailyWork has been registered as launched() in pref`(){
        assertThat(fakePreferenceProvider.dailyWorkLaunched(),IsEqual(true))
    }
}