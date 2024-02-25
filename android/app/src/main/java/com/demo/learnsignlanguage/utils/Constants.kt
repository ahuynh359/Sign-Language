package com.demo.learnsignlanguage.utils

import com.demo.learnsignlanguage.R
import com.demo.learnsignlanguage.data.model.Message
import com.demo.learnsignlanguage.data.model.Question
import com.demo.learnsignlanguage.data.model.TempUser

object Constants {
    const val SIGN_IN_REQUEST = "signInRequest"
    const val SIGN_UP_REQUEST = "signUpRequest"

    const val USERS = "users"

    const val DISPLAY_NAME = "displayName"
    const val EMAIL = "email"
    const val PHOTO_URL = "photoUrl"
    const val CREATED_AT = "createdAt"
    const val NAME = "name"

    const val ERROR_MESSAGE = "Unexpected error !"

    const val USERS_REF = "users"
    const val ACCESS_TOKEN =
        "eyJhbGciOiJSUzI1NiIsImtpZCI6ImExODE4ZjQ0ODk0MjI1ZjQ2MWQyMmI1NjA4NDcyMDM3MTc2MGY1OWIiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiSHXhu7NuaCBOZ-G7jWMgw4FuaCIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJclVRR2tBeWxwaXlZQ1VvWlhrZzAtUVo4M0xZS0Z6Q0xRc2tTaVJ1Qm80YnhIPXM5Ni1jIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL3NpZ24tbGFuZ3VhZ2UtZjQ3NTIiLCJhdWQiOiJzaWduLWxhbmd1YWdlLWY0NzUyIiwiYXV0aF90aW1lIjoxNzA4NzYxNzU0LCJ1c2VyX2lkIjoiSDVBUHZNeG1LYWE0SmVKdER5ajlNQnpOY1k3MyIsInN1YiI6Ikg1QVB2TXhtS2FhNEplSnREeWo5TUJ6TmNZNzMiLCJpYXQiOjE3MDg3NjU0MDIsImV4cCI6MTcwODc2OTAwMiwiZW1haWwiOiJhaHV5bmgzNTlAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDMyMzExMTU3MzQwMDc0MzA2ODkiXSwiZW1haWwiOlsiYWh1eW5oMzU5QGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.c7uAuaMyTk7Ed52pzTx2e3SqzGw5vTTmr0bdxATHStcocvv6EZ3KTSYrPTE-ZFvjos-EHK7xmqfwPKWDkqIfMkV2KZ-8afRYNsVHqG_HAqXlZN1mnVk22fzImdhIkBxa0z3ZOGFbOMb3r7cgK92oWGP0-qwfmy0UlKuo0GRCgK_m6RcyZQIgAbF3emy4g3TkMtAANvyQ7Lmbn9tcMq1AmRx7UE8VkA05lLMsp_L6pSw_LvGCOuXMTqSIz-epxIZLdaSuEhjA7bFRPhh2gkG1tgrQn2NEnGPyvmEqQzaDk2maSV8O4ZShT6kVpV7UYcPU7OIesXJs3-p7rZleHGwDuA"

    val users = mutableListOf<TempUser>(
        TempUser("Huynh Ngoc Anh",0),
        TempUser("Nguyen Duc Chi Danh",1),
        TempUser("Nguyen Duc Nguyen",0),
        TempUser("Tran Long",1),
    )

    val messagesList = mutableListOf<Message>(
        Message("11:59","Hello"),
        Message("12:05","Hi"),
        Message("13:05","How are you ?"),
    )

    val questions = mutableListOf<Question>(
        Question(
            0,
            R.drawable.n0,
            mutableListOf("0", "2", "4", "3"),
            0
        ),
        Question(
            1,
            R.drawable.n1,
            mutableListOf("1", "2", "3", "4"),
            0
        ),
        Question(
            2,
            R.drawable.n2,
            mutableListOf("3", "2", "5", "4"),
            1
        ),
        Question(
            3,
            R.drawable.n3,
            mutableListOf("4", "2", "5", "3"),
            3
        ),
        Question(
            4,
            R.drawable.n4,
            mutableListOf("4", "1", "5", "3"),
            0
        ),
        Question(
            5,
            R.drawable.n5,
            mutableListOf("1", "5", "6", "3"),
            1
        ),
        Question(
            6,
            R.drawable.n6,
            mutableListOf("4", "7", "5", "6"),
            3
        ),
        Question(
            7,
            R.drawable.n7,
            mutableListOf("4", "7", "8", "3"),
            1
        ),
        Question(
            8,
            R.drawable.n8,
            mutableListOf("8", "2", "5", "9"),
            0
        ),
        Question(
            9,
            R.drawable.n9,
            mutableListOf("0", "2", "4", "9"),
            3
        ),
        Question(
            10,
            R.drawable.a,
            mutableListOf("a", "b", "c", "d"),
            0
        ),
        Question(
            11,
            R.drawable.b,
            mutableListOf("t", "b", "d", "p"),
            1
        ),
        Question(
            12,
            R.drawable.c,
            mutableListOf("c", "f", "z", "g"),
            0
        ),
        Question(
            13,
            R.drawable.d,
            mutableListOf("q", "d", "r", "m"),
            1
        ),
        Question(
            14,
            R.drawable.e,
            mutableListOf("e", "x", "l", "w"),
            0
        ),
        Question(
            15,
            R.drawable.f,
            mutableListOf("u", "i", "f", "h"),
            2
        ),
        Question(
            16,
            R.drawable.g,
            mutableListOf("j", "g", "n", "k"),
            1
        ),
        Question(
            17,
            R.drawable.h,
            mutableListOf("v", "y", "h", "o"),
            2
        ),
        Question(
            18,
            R.drawable.i,
            mutableListOf("i", "s", "p", "r"),
            0
        ),
        Question(
            19,
            R.drawable.j,
            mutableListOf("j", "a", "t", "q"),
            0
        ),
        Question(
            20,
            R.drawable.k,
            mutableListOf("b", "k", "f", "e"),
            1
        ),
        Question(
            21,
            R.drawable.l,
            mutableListOf("l", "m", "n", "o"),
            0
        ),
        Question(
            22,
            R.drawable.m,
            mutableListOf("p", "q", "r", "m"),
            3
        ),
        Question(
            23,
            R.drawable.n,
            mutableListOf("s", "t", "u", "n"),
            3
        ),
        Question(
            24,
            R.drawable.o,
            mutableListOf("v", "w", "x", "o"),
            3
        ),
        Question(
            25,
            R.drawable.p,
            mutableListOf("y", "z", "a", "p"),
            3
        ),
        Question(
            26,
            R.drawable.q,
            mutableListOf("b", "c", "d", "q"),
            3
        ),
        Question(
            27,
            R.drawable.r,
            mutableListOf("e", "f", "g", "r"),
            3
        ),
        Question(
            28,
            R.drawable.s,
            mutableListOf("h", "i", "j", "s"),
            3
        ),
        Question(
            29,
            R.drawable.t,
            mutableListOf("k", "l", "m", "t"),
            3
        ),
        Question(
            30,
            R.drawable.u,
            mutableListOf("n", "o", "p", "u"),
            3
        ),
        Question(
            31,
            R.drawable.v,
            mutableListOf("q", "r", "v", "w"),
            2
        ),
        Question(
            32,
            R.drawable.w,
            mutableListOf("x", "y", "z", "w"),
            3
        ),
        Question(
            33,
            R.drawable.x,
            mutableListOf("a", "b", "c", "x"),
            3
        ),
        Question(
            34,
            R.drawable.y,
            mutableListOf("d", "e", "f", "y"),
            3
        ),
        Question(
            35,
            R.drawable.z,
            mutableListOf("g", "h", "i", "z"),
            3
        )
    )
}