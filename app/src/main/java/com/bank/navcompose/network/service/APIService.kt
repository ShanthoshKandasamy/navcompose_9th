package com.bank.navcompose.network.service

import com.bank.navcompose.data.model.People
import com.bank.navcompose.data.model.Planets
import com.bank.navcompose.network.response.PeopleResponse
import com.bank.navcompose.network.response.PlanetResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET(" people/")
    suspend fun getPeoples(): PeopleResponse

    @GET(" people/{id}")
    suspend fun getPeople(@Path(value = "id") id: Int): People

    @GET(" planets")
    suspend fun getPlanets(): PlanetResponse

    @GET("planets/{id}")
    suspend fun getPlanet(@Path(value = "id") id: Int): Planets
}