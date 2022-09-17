package com.wapp.factsapp.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.wapp.factsapp.getOrAwaitValueAndroid
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import org.junit.Assert.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class AppDataBaseTest {
    private lateinit var db: AppDatabase
    private lateinit var factDao: FactDao
    private lateinit var userDao: UserDao
    private val user = User("1","Eric")
    private val fact = Fact("1","Sport", isLiked = false, isBookmarked = false)
    private val fact2 = Fact("2","Cinema",isLiked = true,isBookmarked = true)
    private val fact3 = Fact("3","Sport",isLiked = true,isBookmarked = true)
    private val allFacts = listOf(fact,fact2,fact3)
    private val likedFacts = listOf(fact2,fact3)
    private val bookmarkedFacts = listOf(fact2,fact3)
    private val sportFacts = listOf(fact,fact3)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setDataBase(){
        val context= InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        factDao = db.factDao
        userDao = db.userDao
    }

    //User Tests
    @Test
    fun insertAndGetUser() = runBlockingTest{
        userDao.insert(user)
        val x = userDao.getLocalUser("1")
        val y = userDao.getLiveLocalUser("1")
        assertThat(x,IsEqual(user))
        assertThat(y.getOrAwaitValueAndroid(),IsEqual(user))
    }

    @Test
    fun updateUser() = runBlockingTest {
        userDao.insert(user)
        val x = userDao.getLocalUser("1")
        x.apply {
            this.name = "Henry"
        }
        userDao.updateLocalUser(x)
        assertThat(userDao.getLocalUser("1").name,IsEqual("Henry"))
    }

    @Test
    fun deleteUser() = runBlockingTest {
        userDao.insert(user)
        userDao.deleteLocalUser(user)
        assertNull(userDao.getLocalUser("1"))
    }

    //Fact tests
    @Test
    fun insertAndGetFact() = runBlockingTest{
        factDao.insert(fact)
        assertThat(factDao.getLocalFact("1").getOrAwaitValueAndroid(),IsEqual(fact))
    }

    @Test
    fun updateFact() = runBlockingTest {
        factDao.insert(fact)
        val fact = factDao.getFact("1")
        fact.isBookmarked = true
        factDao.updateFact(fact)
        assertThat(factDao.getLocalFact("1").getOrAwaitValueAndroid().isBookmarked,IsEqual(true))
    }

    @Test
    fun insertAllFacts() = runBlockingTest{
        factDao.insertAll(allFacts)
        assertThat(factDao.getAllFacts(),IsEqual(allFacts))
    }

    @Test
    fun getFavoriteFacts() = runBlockingTest{
        factDao.insertAll(allFacts)
        assertThat(factDao.getFavoriteFacts(true),IsEqual(likedFacts))
    }

    @Test
    fun getBookMarkedFacts() = runBlockingTest {
        factDao.insertAll(allFacts)
        assertThat(factDao.getBookmarkedFacts(true),IsEqual(bookmarkedFacts))
    }

    @Test
    fun getFactsByCategory() = runBlockingTest {
        factDao.insertAll(allFacts)
        assertThat(factDao.getFactsByCategory("Sport"),IsEqual(sportFacts))
    }

    @After
    @Throws(IOException::class)
    fun closeDataBase(){
        db.close()
    }

}