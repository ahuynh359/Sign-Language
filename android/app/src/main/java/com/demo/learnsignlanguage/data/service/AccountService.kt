package com.demo.learnsignlanguage.data.service

import com.demo.learnsignlanguage.data.api.MessageJson
import com.demo.learnsignlanguage.data.api.UserApi
import com.demo.learnsignlanguage.network.NetworkResult
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("/api/v1/auth/register/")
    suspend fun createNewAccount(@Body userApi : UserApi) : NetworkResult<MessageJson>
}