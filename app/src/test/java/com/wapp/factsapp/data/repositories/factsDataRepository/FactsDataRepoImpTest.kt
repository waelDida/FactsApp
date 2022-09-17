package com.wapp.factsapp.data.repositories.factsDataRepository

import com.wapp.factsapp.data.sources.fact.FakeFactDataSource
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FactsDataRepoImpTest{

    //class under test
    private lateinit var factsDataRepoImp: FactsDataRepoImp

    private var fact1 = Fact("1")
    private var fact2 = Fact("2")
    private var fact3 = Fact("3")
    private var fact4 = Fact(id="4",isBookmarked = true)
    private var fact5 = Fact(id="5",isLiked = true)
    private var fact6 = Fact(id="6",category = "sport")
    private var factRemoteList = mutableListOf(fact1,fact2,fact3,fact4,fact5,fact6)

    @Before
    fun createRepository(){
        val fakeFactDataSource = FakeFactDataSource(factRemoteList)
        factsDataRepoImp = FactsDataRepoImp(fakeFactDataSource)
    }

    @Test
    fun `get a single remote fact from fireStore`() = runBlockingTest{
        val x = factsDataRepoImp.getFactFromFireStore("2")
        assertThat(x, IsEqual(fact2))
    }

    @Test
    fun `get all remote facts and save it in room`() = runBlockingTest{
        factsDataRepoImp.getAndSaveAllRemoteFactsIntoRoom()
        val localList = factsDataRepoImp.getAllFacts()
        assertThat(localList,IsEqual(factRemoteList))
    }

    @Test
    fun `get facts by category`() = runBlockingTest {
        val x = factsDataRepoImp.getFactsByCategory("sport")
        assertThat(x[0],IsEqual(fact6))
    }

    @Test
    fun `get Bookmarked fact`() = runBlockingTest {
        val x = factsDataRepoImp.getBookmarkedFacts() as Resource.Success
        assertThat(x.data[0],IsEqual(fact4))
    }

    @Test
    fun `get liked facts`() = runBlockingTest {
        val x = factsDataRepoImp.getFavoriteFacts() as Resource.Success
        assertThat(x.data[0],IsEqual(fact5))
    }
}