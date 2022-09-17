package com.wapp.factsapp.data.repositories.userDataRepositoy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.sources.user.FakeUserDataSource
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDataRepoImpTest{
    private lateinit var userDataRepoImp: UserDataRepoImp
    private lateinit var fakeUserDataSource: FakeUserDataSource

    private val user1 = User("1")


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun initRepo(){
        fakeUserDataSource = FakeUserDataSource()
        userDataRepoImp = UserDataRepoImp(fakeUserDataSource)
    }

    @Test
    fun `create and get user from fireStore`() = runBlockingTest {
        userDataRepoImp.createNewUserInFireStore(user1)
        val x = userDataRepoImp.getCurrentUserFromFireStore(user1.uid)
        assertThat(x,IsEqual(user1))
    }

    @Test
    fun `insert and get user from room`() = runBlockingTest {
        userDataRepoImp.insertUserIntoRoom(user1)
        val x = userDataRepoImp.getUserFromRoom(user1.uid)
        assertThat(x,IsEqual(user1))
    }

    @Test
    fun `get live user from room`() = runBlockingTest {
        userDataRepoImp.insertUserIntoRoom(user1)
        val x = userDataRepoImp.getLiveUserFromRoom(user1.uid)
        assertThat(x.getOrAwaitValue(),IsEqual(user1))
    }

    @Test
    fun `update a user in fireStore`() = runBlockingTest {
        userDataRepoImp.createNewUserInFireStore(user1)
        userDataRepoImp.updateUserField(user1.uid,"coins",20)
        val x = userDataRepoImp.getCurrentUserFromFireStore(user1.uid)
        assertThat(x!!.coins,IsEqual(20))
    }
}