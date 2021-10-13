package com.bank.navcompose.network.model.planets

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("climate")
    val climate: String? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("diameter")
    val diameter: String? = null,
    @SerializedName("edited")
    val edited: String? = null,
    @SerializedName("films")
    val films: List<String>? = null,
    @SerializedName("gravity")
    val gravity: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("orbital_period")
    val orbital_period: String? = null,
    @SerializedName("population")
    val population: String? = null,
    @SerializedName("residents")
    val residents: List<String>? = null,
    @SerializedName("rotation_period")
    val rotation_period: String? = null,
    @SerializedName("surface_water")
    val surface_water: String? = null,
    @SerializedName("terrain")
    val terrain: String? = null,
    @SerializedName("url")
    val url: String? = null
)