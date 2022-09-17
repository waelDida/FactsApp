package com.wapp.factsapp.flow.ui.bookmarks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import kotlinx.coroutines.Dispatchers


class BookmarksViewModel @ViewModelInject constructor(private val factsDataRepo: FactsDataRepo): ViewModel() {

    val fetchedData = liveData(Dispatchers.IO) {
        val x = factsDataRepo.getBookmarkedFacts()
        emit(x)
    }

}