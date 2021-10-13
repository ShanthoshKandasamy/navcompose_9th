package com.bank.navcompose.repository

import com.bank.navcompose.data.model.People
import com.bank.navcompose.network.CoroutineDispatchWrapper
import com.bank.navcompose.network.response.PeopleResponse
import com.bank.navcompose.util.Resource
import com.bank.navcompose.network.service.APIService
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface IPeopleRepository {
    fun getPeoples(): Flow<Resource<PeopleResponse>>
    fun getPeople(id: Int): Flow<Resource<People>>
}

@Singleton
class PeopleRepository @Inject constructor(
    private val api: APIService,
    private val dispatcher: CoroutineDispatchWrapper
) : IPeopleRepository {
    override fun getPeoples(): Flow<Resource<PeopleResponse>> =
        channelFlow {
            withContext(Job() + dispatcher.io()) {
                offer(Resource.Loading())
                try {
                    val result = api.getPeoples()
                    offer(Resource.Success(result))
                } catch (e: Exception) {
                    offer(
                        Resource.Error<PeopleResponse>(
                            message = "Unable to get API response, pull down to refresh"
                        )
                    )
                }
            }
        }

    override fun getPeople(id: Int): Flow<Resource<People>> =
        channelFlow {
            withContext(Job() + dispatcher.io()) {
                offer(Resource.Loading())
                try {
                    val result = api.getPeople(id = id)
                    offer(Resource.Success(result))
                } catch (e: Exception) {
                    offer(
                        Resource.Error<People>(
                            message = "Unable to get API response, pull down to refresh"
                        )
                    )
                }
            }
        }
}
