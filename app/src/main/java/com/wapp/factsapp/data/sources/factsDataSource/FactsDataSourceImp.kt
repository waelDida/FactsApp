package com.wapp.factsapp.data.sources.factsDataSource


import androidx.lifecycle.LiveData
import com.wapp.factsapp.api.getAllFactsInCategoriesFromFireStore
import com.wapp.factsapp.api.getOneRemoteFact
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource
import com.wapp.factsapp.room.AppDatabase
import com.wapp.factsapp.utils.convertToFactList
import javax.inject.Inject

class FactsDataSourceImp @Inject constructor(private val appDatabase: AppDatabase): FactsDataSource {

    override suspend fun getFactFromFireStore(id: String): Fact {
        val documentSnapshot = getOneRemoteFact(id)
        return documentSnapshot.toObject(Fact::class.java)!!
    }

    override suspend fun getAllRemoteFactsInCategories(): List<Fact> {
        val documentSnapshots = getAllFactsInCategoriesFromFireStore()
        return documentSnapshots.convertToFactList()
    }

    override suspend fun insertFactIntoRoom(fact: Fact) {
        appDatabase.factDao.insert(fact)
    }

    override suspend fun insertAllFactsIntoRoom(facts:List<Fact>) {
       appDatabase.factDao.insertAll(facts)
    }

    override suspend fun getFact(id: String): Fact {
        return appDatabase.factDao.getFact(id)
    }

    override fun getLocalFact(id: String):LiveData<Fact> {
        return appDatabase.factDao.getLocalFact(id)
    }


    override suspend fun updateLocalFact(fact: Fact) {
        appDatabase.factDao.updateFact(fact)
    }

    override suspend fun getAllFactsStoredInLocal(): List<Fact> {
        return appDatabase.factDao.getAllFacts()
    }

    override fun getAllLiveFactsStoredInLocal(): LiveData<List<Fact>> {
        return appDatabase.factDao.getAllLiveFacts()
    }

    override suspend fun getFactsByCategory(category: String): List<Fact> {
        return appDatabase.factDao.getFactsByCategory(category)
    }

    override suspend fun getBookmarkedFacts(): Resource<List<Fact>> {
       val list = appDatabase.factDao.getBookmarkedFacts(true)
        return if(list.isNotEmpty()) Resource.Success(list) else Resource.Empty(list)
    }

    override suspend fun getFavoriteFacts(): Resource<List<Fact>> {
        val list  = appDatabase.factDao.getFavoriteFacts(true)
        return if(list.isNotEmpty()) Resource.Success(list) else Resource.Empty(list)
    }

    override suspend fun getDailyFacts(value: Boolean): List<Fact> {
        return appDatabase.factDao.getDailyFacts(value)
    }
}