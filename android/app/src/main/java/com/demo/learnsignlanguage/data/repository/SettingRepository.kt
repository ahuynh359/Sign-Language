package com.demo.learnsignlanguage.data.repository

import com.demo.learnsignlanguage.data.model.User
import com.demo.learnsignlanguage.network.NetworkResult
import com.demo.learnsignlanguage.utils.Constants.ERROR_MESSAGE
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SettingRepository @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val auth: FirebaseAuth,
    private val usersRef: CollectionReference
) {

    fun signOut() = flow {
        try {
            emit(NetworkResult.Loading)
            googleSignInClient.signOut().await().also {
                emit(NetworkResult.Success(it))
            }
            auth.signOut()

        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.message ?: ERROR_MESSAGE))
        }
    }

    @ExperimentalCoroutinesApi
    fun getFirebaseAuthState() = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }

    }

    suspend fun getUser() = flow {
        try {
            emit(NetworkResult.Loading)
            val currentUser = auth.currentUser
            val idToken = currentUser?.getIdToken(true)?.await()?.token

            val user = User(
                currentUser?.displayName,
                currentUser?.email,
                currentUser?.photoUrl.toString(),
                idToken.toString()
            )
            emit(NetworkResult.Success(user))
//            auth.currentUser?.apply {
//                val user = usersRef.document(uid).get().await().toObject(User::class.java)
//                user?.let {
//                    emit(NetworkResult.Success(user))
//                }
//            }
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.message ?: ERROR_MESSAGE))
        }
    }

}
