package com.wapp.factsapp.data.repositories.factsDataRepository

import androidx.lifecycle.LiveData
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource

interface FactsDataRepo {

    //Remote
    suspend fun getFactFromFireStore(id: String): Fact
    suspend fun getAllFactsFromFireStore():List<Fact>

    //Local
    suspend fun insertFactIntoRoom(fact: Fact)
    suspend fun insertAllFactsIntoRoom(list: List<Fact>)
    suspend fun getFact(id:String):Fact
    fun getLocalFact(id:String):LiveData<Fact>
    suspend fun updateLocalFact(fact: Fact)
    suspend fun getAllFacts(): List<Fact>
    fun getAllLiveFacts(): LiveData<List<Fact>>
    suspend fun getFactsByCategory(category: String):List<Fact>
    suspend fun getBookmarkedFacts(): Resource<List<Fact>>
    suspend fun getFavoriteFacts(): Resource<List<Fact>>
    suspend fun getDailyFacts(value: Boolean): List<Fact>


    //Remote & Local
    suspend fun getAndSaveRemoteFactIntoRoom(id: String)
    suspend fun getAndSaveAllRemoteFactsIntoRoom()

}