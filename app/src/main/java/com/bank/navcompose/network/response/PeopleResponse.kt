package com.bank.navcompose.network.response

import com.bank.navcompose.network.model.people.Result
import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)