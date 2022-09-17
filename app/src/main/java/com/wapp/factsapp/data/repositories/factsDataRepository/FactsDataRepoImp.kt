package com.wapp.factsapp.data.repositories.factsDataRepository

import androidx.lifecycle.LiveData
import com.wapp.factsapp.data.sources.factsDataSource.FactsDataSource
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource
import javax.inject.Inject

class FactsDataRepoImp @Inject constructor(private val factsDataSource: FactsDataSource): FactsDataRepo {
    override suspend fun getFactFromFireStore(id: String): Fact {
        return factsDataSource.getFactFromFireStore(id)
    }

    override suspend fun getAllFactsFromFireStore(): List<Fact> {
        return factsDataSource.getAllRemoteFactsInCategories()
    }

    override suspend fun insertFactIntoRoom(fact: Fact) {
        factsDataSource.insertFactIntoRoom(fact)
    }

    override suspend fun insertAllFactsIntoRoom(list: List<Fact>) {
        factsDataSource.insertAllFactsIntoRoom(list)
    }

    override suspend fun getFact(id: String): Fact {
        return factsDataSource.getFact(id)
    }

    override fun getLocalFact(id: String): LiveData<Fact> {
        return factsDataSource.getLocalFact(id)
    }

    override suspend fun updateLocalFact(fact: Fact) {
        factsDataSource.updateLocalFact(fact)
    }

    override suspend fun getAllFacts(): List<Fact> {
        return factsDataSource.getAllFactsStoredInLocal()
    }

    override fun getAllLiveFacts(): LiveData<List<Fact>> {
        return factsDataSource.getAllLiveFactsStoredInLocal()
    }

    override suspend fun getFactsByCategory(category: String): List<Fact> {
        return factsDataSource.getFactsByCategory(category)
    }

    override suspend fun getBookmarkedFacts(): Resource<List<Fact>> {
        return factsDataSource.getBookmarkedFacts()
    }

    override suspend fun getFavoriteFacts(): Resource<List<Fact>> {
        return factsDataSource.getFavoriteFacts()
    }

    override suspend fun getDailyFacts(value: Boolean): List<Fact> {
        return factsDataSource.getDailyFacts(value)
    }

    override suspend fun getAndSaveRemoteFactIntoRoom(id: String) {
        val remoteFact = factsDataSource.getFactFromFireStore(id)
        factsDataSource.insertFactIntoRoom(remoteFact)
    }

    override suspend fun getAndSaveAllRemoteFactsIntoRoom() {
        val x = factsDataSource.getAllRemoteFactsInCategories()
        factsDataSource.insertAllFactsIntoRoom(x)
    }
}