package com.demo.learnsignlanguage.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.learnsignlanguage.data.repository.SplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository : SplashRepository) : ViewModel() {

    val isUserAuthenticated get() = repository.isUserAuthenticated
}