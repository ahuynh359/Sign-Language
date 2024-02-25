package com.demo.learnsignlanguage.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.demo.learnsignlanguage.network.NetworkResult
import com.demo.learnsignlanguage.utils.Constants.CREATED_AT
import com.demo.learnsignlanguage.utils.Constants.EMAIL
import com.demo.learnsignlanguage.utils.Constants.ERROR_MESSAGE
import com.demo.learnsignlanguage.utils.Constants.NAME
import com.demo.learnsignlanguage.utils.Constants.PHOTO_URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val usersRef : CollectionReference,

) {

    suspend fun firebaseSignInWithGoogle(idToken: String) = flow {
        try {
            emit(NetworkResult.Loading)
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            authResult.additionalUserInfo?.apply {
                emit(NetworkResult.Success(isNewUser))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.message ?: ERROR_MESSAGE))
        }
    }

    suspend fun createUserInFirestore()  = flow{
        try {
            emit(NetworkResult.Loading)
            auth.currentUser?.apply {
                usersRef.document(uid).set(mapOf(
                    NAME to displayName,
                    EMAIL to email,
                    PHOTO_URL to photoUrl?.toString(),
                    CREATED_AT to serverTimestamp()

                )).await().also {
                    emit(NetworkResult.Success(it))
                }
            }
        } catch (e : Exception){
            emit(NetworkResult.Failure(e.message ?: ERROR_MESSAGE))
        }
    }


}