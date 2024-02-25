package com.demo.learnsignlanguage.data.model

import androidx.annotation.DrawableRes

data class Question(
    val id : Int,
    @DrawableRes val image: Int,
    val ans : MutableList<String>,
    val correctAns: Int
)