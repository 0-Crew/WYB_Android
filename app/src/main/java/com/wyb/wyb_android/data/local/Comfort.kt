package com.wyb.wyb_android.data.local

data class Comfort(
    val range: String,
    val comfort: String,
    val innerList: MutableList<Challenge>
)
