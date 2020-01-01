package com.goodarzi.kishvira.model.fire


import com.google.gson.annotations.SerializedName

data class Fire(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: Int
)