package com.goodarzi.kishvira.model.fire


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Address")
    val address: String,
    @SerializedName("CityName")
    val cityName: String,
    @SerializedName("DamageDate")
    val damageDate: String,
    @SerializedName("DamageTime")
    val damageTime: String,
    @SerializedName("History")
    val history: List<History>,
    @SerializedName("OwnerShipType")
    val ownerShipType: Int,
    @SerializedName("PolicyNumber")
    val policyNumber: String,
    @SerializedName("ProvinceName")
    val provinceName: String
)