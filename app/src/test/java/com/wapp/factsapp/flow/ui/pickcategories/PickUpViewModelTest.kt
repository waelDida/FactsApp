package com.wapp.factsapp.flow.ui.pickcategories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.utils.categoriesNameList
import com.wapp.factsapp.utils.categoriesToPickList
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test



class PickUpViewModelTest{
    private lateinit var pickUpViewModel: PickUpViewModel
    private lateinit var fakePrefProvider: FakePrefProvider
    private var categoriesNames = categoriesNameList()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun initViewModel(){
        fakePrefProvider = FakePrefProvider()
        fakePrefProvider.setPickedCategories(categoriesNames.toSet())
        pickUpViewModel = PickUpViewModel(fakePrefProvider)
    }

    @Test
    fun`verify that the pickup list is the same stored in pref`(){
        assertThat(pickUpViewModel.pickUpList.getOrAwaitValue().size,IsEqual(15))
        assertThat(pickUpViewModel.categoriesNameList.size,IsEqual(15))
    }

    @Test
    fun addOrRemoveFromPickUpList(){
        val sport = categoriesToPickList().filter { it.name == "Sport" }[0]
        sport.isPicked = false
        pickUpViewModel.addOrRemoveFromPickUpList(sport)
        assertThat(pickUpViewModel.categoriesNameList.contains("Sport"),IsEqual(false))
    }

    @Test
    fun `update the pickup list in pref`(){
        val cat = categoriesToPickList().filter { it.name == "Sport" }[0]
        cat.isPicked = false
        pickUpViewModel.addOrRemoveFromPickUpList(cat)
        pickUpViewModel.updatePickUpList(pickUpViewModel.categoriesNameList)
        assertThat(fakePrefProvider.getPickedCategories().contains("Sport"),IsEqual(false))
    }
}