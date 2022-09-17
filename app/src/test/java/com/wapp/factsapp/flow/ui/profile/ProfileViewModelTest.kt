package com.wapp.factsapp.flow.ui.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProfileViewModelTest{

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFireBaseProvider: FakeFireBaseProvider
    private lateinit var preferenceProvider: PreferenceProvider

    private val user = User("1",imgUrl = "old url")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun intViewModel() = runBlockingTest{
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeUserRepository = FakeUserRepository()
        fakeUserRepository.createNewUserInFireStore(user)
        fakeUserRepository.insertUserIntoRoom(user)
        fakeFireBaseProvider = FakeFireBaseProvider()
        fakeFireBaseProvider.setId("1")
        fakeFireBaseProvider.setUploadFlag("new url")
        preferenceProvider = FakePrefProvider()
        profileViewModel = ProfileViewModel(fakeFireBaseProvider,fakeUserRepository,preferenceProvider)
        profileViewModel.uploadImageToFirebase(null)
    }

    @Test
    fun`verify that the photo has been uploaded and saved successfully`() = runBlockingTest {
        val localUser = fakeUserRepository.getUserFromRoom(fakeFireBaseProvider.getCurrentUserId())
        val remoteUser = fakeUserRepository.getCurrentUserFromFireStore(fakeFireBaseProvider.getCurrentUserId())
        assertThat(localUser.imgUrl,IsEqual("new url"))
        assertThat(remoteUser.imgUrl,IsEqual("new url"))
        assertThat(profileViewModel.imageSaved.getOrAwaitValue(),IsEqual(true))
    }

}