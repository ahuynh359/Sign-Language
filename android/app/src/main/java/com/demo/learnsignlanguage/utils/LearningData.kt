package com.demo.learnsignlanguage.utils

import androidx.annotation.DrawableRes
import com.demo.learnsignlanguage.R

enum class LearningData(@DrawableRes val image : Int, val nameCard : String) {
    ASL(R.drawable.asl,"ASL Language"),
    Num(R.drawable.number,"ASL Number"),
    Alphabet(R.drawable.alphabet,"ASL Alphabet"),
    Word(R.drawable.word,"ASL Word"),
}