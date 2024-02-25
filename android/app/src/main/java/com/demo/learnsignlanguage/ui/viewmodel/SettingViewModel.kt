package com.demo.learnsignlanguage.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.demo.learnsignlanguage.data.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val repository : SettingRepository) : ViewModel() {

    fun signOut() = liveData(Dispatchers.IO) {
        repository.signOut().collect{ response ->
            emit(response)
        }
    }

    @ExperimentalCoroutinesApi
    fun getAuthState() = liveData(Dispatchers.IO) {
        repository.getFirebaseAuthState().collect { res ->
            emit(res)
        }
    }

    fun getUser() = liveData(Dispatchers.IO) {
        repository.getUser().collect() { response ->
            emit(response)
        }
    }


}
