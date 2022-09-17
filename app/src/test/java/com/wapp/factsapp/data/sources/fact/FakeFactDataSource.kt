package com.wapp.factsapp.data.sources.fact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wapp.factsapp.data.sources.factsDataSource.FactsDataSource
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource

class FakeFactDataSource(private val list: MutableList<Fact> = mutableListOf()): FactsDataSource {

    private var localList = mutableListOf<Fact>()
    private lateinit var liveLocalList: MutableLiveData<List<Fact>>

    override suspend fun getFactFromFireStore(id: String): Fact {
        return list.filter { it.id == id }[0]
    }

    override suspend fun getAllRemoteFactsInCategories(): List<Fact> {
        return list
    }

    override suspend fun insertFactIntoRoom(fact: Fact) {
        localList.add(fact)
    }

    override suspend fun insertAllFactsIntoRoom(facts: List<Fact>) {
        localList.addAll(facts)
    }

    override suspend fun getFact(id: String): Fact {
        TODO("Not yet implemented")
    }

    override fun getLocalFact(id: String): LiveData<Fact> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocalFact(fact: Fact) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFactsStoredInLocal(): List<Fact> {
        return localList
    }

    override fun getAllLiveFactsStoredInLocal(): LiveData<List<Fact>> {
        liveLocalList.value = localList
        return liveLocalList
    }

    override suspend fun getFactsByCategory(category: String): List<Fact> {
        localList = list
        return localList.filter { it.category == category}
    }

    override suspend fun getBookmarkedFacts(): Resource<List<Fact>> {
        localList = list
        val list = localList.filter { it.isBookmarked }
        return if(list.isNotEmpty()) Resource.Success(list) else Resource.Empty(list)
    }

    override suspend fun getFavoriteFacts(): Resource<List<Fact>> {
        localList = list
        val list = localList.filter{ it.isLiked }
        return if(list.isNotEmpty()) Resource.Success(list) else Resource.Empty(list)
    }

    override suspend fun getDailyFacts(value: Boolean): List<Fact> {
        TODO("Not yet implemented")
    }
}