package com.demo.learnsignlanguage.data.repository

import android.graphics.BitmapFactory
import com.demo.learnsignlanguage.data.service.CardService
import com.demo.learnsignlanguage.network.NetworkResult
import com.demo.learnsignlanguage.utils.Constants
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(
    private val cardService: CardService,
    private val storageRef: StorageReference
) {
    suspend fun getCards() = flow {
        try {
            emit(NetworkResult.Loading)
            val response = cardService.getCards()
            if (response.isSuccessful) {
                val data = response.body()
                emit(NetworkResult.Success(data))

            }
        } catch (e: Exception) {
            NetworkResult.Failure(e.message.toString())
        }
    }


    suspend fun getImageFromStorage(imagePath: String) = flow {
        try {
            emit(NetworkResult.Loading)
            val imageRef = storageRef.child(imagePath)
            val byteArray = getImageByteArray(imageRef)
            if (byteArray != null) {
                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                emit(NetworkResult.Success(byteArray))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.message ?: Constants.ERROR_MESSAGE))
        }

    }


    private suspend fun getImageByteArray(storageRef: StorageReference): ByteArray? {
        return try {
            val MAX_SIZE: Long = 1024 * 1024 // 1 MB
            val byteArrayOutputStream = ByteArrayOutputStream()
            storageRef.getBytes(MAX_SIZE).await().let { task ->
                byteArrayOutputStream.write(task)
            }
            byteArrayOutputStream.toByteArray()
        } catch (e: Exception) {
            null
        }
    }
}

