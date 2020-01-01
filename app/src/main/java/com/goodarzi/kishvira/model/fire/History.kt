package com.goodarzi.kishvira.model.fire


import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("CreateDateString")
    val createDateString: String,
    @SerializedName("CreateDateTime")
    val createDateTime: String,
    @SerializedName("CreateTimeString")
    val createTimeString: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("HistoryAble")
    val historyAble: Boolean,
    @SerializedName("State")
    val state: Int,
    @SerializedName("Status")
    val status: Int,
    @SerializedName("StatusTitle")
    val statusTitle: String,
    @SerializedName("UserName")
    val userName: String
)