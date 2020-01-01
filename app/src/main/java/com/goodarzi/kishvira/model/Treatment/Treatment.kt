package com.goodarzi.kishvira.model.Treatment


import com.google.gson.annotations.SerializedName

data class Treatment(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Status")
    val status: Int
)