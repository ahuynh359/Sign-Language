package com.demo.learnsignlanguage.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.demo.learnsignlanguage.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {
    fun signInWithGoogle(idToken: String) = liveData(Dispatchers.IO) {
        repository.firebaseSignInWithGoogle(idToken).collect { response ->
            emit(response)
        }
    }

    fun createUser() = liveData(Dispatchers.IO) {
        repository.createUserInFirestore().collect{
            response -> emit(response)
        }
    }
}
