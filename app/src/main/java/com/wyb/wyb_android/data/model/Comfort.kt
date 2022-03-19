package com.wyb.wyb_android.data.model

data class Comfort(
    val range: String,
    val comfort: String,
    val innerList: MutableList<Challenge>
)
