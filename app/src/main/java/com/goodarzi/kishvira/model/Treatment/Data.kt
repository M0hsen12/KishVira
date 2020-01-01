package com.goodarzi.kishvira.model.Treatment


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CreateDateTime")
    val createDateTime: String,
    @SerializedName("FileNumber")
    val fileNumber: Int,
    @SerializedName("FinalCost")
    val finalCost: Int,
    @SerializedName("RequestsList")
    val requestsList: List<Requests>,
    @SerializedName("Status")
    val status: Int,
    @SerializedName("StatusTitle")
    val statusTitle: String
)