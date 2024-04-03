package com.yunpnzr.quoteapp.data.source.network.model

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("id")
    val id : Int? = null,
    @SerializedName("quote")
    val quote : String? = null,
    @SerializedName("anime")
    val anime : String? = null,
    @SerializedName("character")
    val character : String? = null
)
