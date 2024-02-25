package com.demo.learnsignlanguage.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.demo.learnsignlanguage.data.api.CardBody
import com.demo.learnsignlanguage.data.repository.CardRepository
import com.demo.learnsignlanguage.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val repository: CardRepository) : ViewModel() {




    fun getCards() = liveData(Dispatchers.IO) {
        repository.getCards().collect() { response ->
            emit(response)
        }

    }

    fun getImage(image: String) = liveData(Dispatchers.IO) {
        repository.getImageFromStorage(image).collect { response ->
            emit(response)
        }
    }
}