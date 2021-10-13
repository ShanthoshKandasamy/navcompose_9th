package com.bank.navcompose.di

import com.bank.navcompose.network.model.people.ResultToPeopleMapper
import com.bank.navcompose.network.model.planets.ResultToPlanetsMapper
import com.bank.navcompose.network.service.APIService
import com.bank.navcompose.util.Constants.Companion.BASE_URL_SW
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providePeopleMapper(): ResultToPeopleMapper {
        return ResultToPeopleMapper()
    }

    @Provides
    fun providePlanetsMapper(): ResultToPlanetsMapper {
        return ResultToPlanetsMapper()
    }

    @Provides
    fun provideRecipeService() : APIService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_SW)
            .client(client)
            .build()
            .create(APIService::class.java)
    }

}