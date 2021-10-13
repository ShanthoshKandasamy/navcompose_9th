package com.bank.navcompose.di

import com.bank.navcompose.repository.IPlanetRepository
import com.bank.navcompose.repository.PlanetRepository
import com.bank.navcompose.network.CoroutineDispatchWrapper
import com.bank.navcompose.network.ThreadDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlanetsModule {

    @Binds
    abstract fun bindPlanetRepository(repository: PlanetRepository): IPlanetRepository

    @Binds
    abstract fun bindsDispatchWrapper(dispatcher: ThreadDispatcher): CoroutineDispatchWrapper
}