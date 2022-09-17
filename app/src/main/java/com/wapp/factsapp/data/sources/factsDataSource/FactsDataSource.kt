package com.wapp.factsapp.data.sources.factsDataSource


import androidx.lifecycle.LiveData
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource

interface FactsDataSource {

    //Remote
    suspend fun getFactFromFireStore(id: String): Fact
    suspend fun getAllRemoteFactsInCategories():List<Fact>

    //Local
    suspend fun insertFactIntoRoom(fact: Fact)
    suspend fun insertAllFactsIntoRoom(facts:List<Fact>)
    suspend fun getFact(id:String):Fact
    fun getLocalFact(id:String):LiveData<Fact>
    suspend fun updateLocalFact(fact:Fact)
    suspend fun getAllFactsStoredInLocal(): List<Fact>
    fun getAllLiveFactsStoredInLocal(): LiveData<List<Fact>>
    suspend fun getFactsByCategory(category: String): List<Fact>
    suspend fun getBookmarkedFacts(): Resource<List<Fact>>
    suspend fun getFavoriteFacts(): Resource<List<Fact>>
    suspend fun getDailyFacts(value: Boolean): List<Fact>

}