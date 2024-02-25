package com.demo.learnsignlanguage.data.api

data class CardBody(
    val message: String,
    val data: List<CardApi>,
    val success: Boolean
)

data class CardApi(
    val id: Int,
//    Fix here
    val front: Int,
    val back: String,
    val category: String,
    var isFront : Boolean = true
)
