package com.demo.learnsignlanguage.data.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SplashRepository @Inject constructor(private val auth : FirebaseAuth){
    val isUserAuthenticated get() = (auth.currentUser != null)
}