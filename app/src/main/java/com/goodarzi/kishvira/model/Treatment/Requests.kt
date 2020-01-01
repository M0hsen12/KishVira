package com.goodarzi.kishvira.model.Treatment


import com.google.gson.annotations.SerializedName

data class Requests(
    @SerializedName("AmountByInsured")
    val amountByInsured: Int,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("LossDateTime")
    val lossDateTime: String,
    @SerializedName("Status")
    val status: Int,
    @SerializedName("StepState")
    val stepState: Int,
    @SerializedName("TreatmentRiskName")
    val treatmentRiskName: String
)