package com.bank.navcompose.repository

import com.bank.navcompose.network.CoroutineDispatchWrapper
import com.bank.navcompose.network.response.PlanetResponse
import com.bank.navcompose.util.Resource
import com.bank.navcompose.network.service.APIService
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface IPlanetRepository {
    fun getPlanets(): Flow<Resource<PlanetResponse>>
}

@Singleton
class PlanetRepository @Inject constructor(
    private val api: APIService,
    private val dispatcher: CoroutineDispatchWrapper
) : IPlanetRepository {
    override fun getPlanets(): Flow<Resource<PlanetResponse>> =
        channelFlow {
            withContext(Job() + dispatcher.io()) {
                offer(Resource.Loading<PlanetResponse>())
                try {
                    val result = api.getPlanets()
                    offer(Resource.Success(result))
                } catch (e: Exception) {
                    offer(
                        Resource.Error<PlanetResponse>(
                            message = "Unable to get API response, pull down to refresh"
                        )
                    )
                }
            }
        }
}
