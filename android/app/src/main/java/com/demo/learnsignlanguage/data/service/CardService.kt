package com.demo.learnsignlanguage.data.service

import com.demo.learnsignlanguage.data.api.CardApi
import com.demo.learnsignlanguage.data.api.CardBody
import com.demo.learnsignlanguage.data.api.MessageJson
import com.demo.learnsignlanguage.data.api.UserApi
import com.demo.learnsignlanguage.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CardService {
    @GET("/api/v1/cards")
    suspend fun getCards(): Response<CardBody>
}