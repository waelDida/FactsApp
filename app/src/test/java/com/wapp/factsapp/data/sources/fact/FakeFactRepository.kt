package com.wapp.factsapp.data.sources.fact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource

class FakeFactRepository :FactsDataRepo {

    private lateinit var remoteList: List<Fact>

    private var facts : LinkedHashMap<String,Fact>  = LinkedHashMap()

    private val localFact  = MutableLiveData<Fact>()
    private val localFactsList = MutableLiveData<List<Fact>>()

    fun addFacts(vararg careSeekers: Fact){
        for(careSeeker in careSeekers)
            facts[careSeeker.id] = careSeeker
    }

    fun setRemoteList(list: List<Fact>){
        remoteList = list
    }

    override suspend fun getFactFromFireStore(id: String): Fact {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFactsFromFireStore(): List<Fact> {
        return remoteList.toList()
    }

    override suspend fun insertFactIntoRoom(fact: Fact) {
        facts[fact.id] = fact
    }

    override suspend fun insertAllFactsIntoRoom(list: List<Fact>) {
        list.forEach {
            facts[it.id] = it
        }
    }

    override suspend fun getFact(id: String): Fact {
        return facts[id]!!
    }

    override fun getLocalFact(id: String): LiveData<Fact> {
        localFact.value = facts[id]
        return localFact
    }

    override suspend fun updateLocalFact(fact: Fact) {
        facts[fact.id] = fact
    }

    override suspend fun getAllFacts(): List<Fact> {
        return ArrayList(facts.values).toList()
    }

    override fun getAllLiveFacts(): LiveData<List<Fact>> {
        localFactsList.value = ArrayList(facts.values).toList()
        return localFactsList
    }

    override suspend fun getFactsByCategory(category: String): List<Fact> {
        return ArrayList(facts.values.filter { it.category == category }).toList()
    }

    override suspend fun getBookmarkedFacts(): Resource<List<Fact>> {
        val list = ArrayList(facts.values.filter { it.isBookmarked }).toList()
        return if(list.isNotEmpty()) Resource.Success(list) else Resource.Empty(list)
    }

    override suspend fun getFavoriteFacts(): Resource<List<Fact>> {
        val list = ArrayList(facts.values.filter { it.isLiked }).toList()
        return if(list.isNotEmpty()) Resource.Success(list) else Resource.Empty(list)
    }

    override suspend fun getDailyFacts(value: Boolean): List<Fact> {
        return ArrayList(facts.values.filter { it.dailyFact == value }).toList()
    }

    override suspend fun getAndSaveRemoteFactIntoRoom(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAndSaveAllRemoteFactsIntoRoom() {
        TODO("Not yet implemented")
    }
}