package com.demo.learnsignlanguage.data.remote

import com.demo.learnsignlanguage.data.api.MessageJson
import com.demo.learnsignlanguage.data.api.UserApi
import com.demo.learnsignlanguage.data.service.AccountService
import com.demo.learnsignlanguage.network.NetworkResult
import javax.inject.Inject

class AccountRemoteService @Inject constructor(
    private val service: AccountService
) {

    suspend fun createNewAccount(userApi: UserApi): NetworkResult<MessageJson> {
        return service.createNewAccount(userApi)
    }
}