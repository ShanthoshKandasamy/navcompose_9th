package com.bank.navcompose.di

import com.bank.navcompose.repository.IPeopleDetailRepository
import com.bank.navcompose.repository.IPeopleRepository
import com.bank.navcompose.repository.PeopleDetailRepository
import com.bank.navcompose.repository.PeopleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PeopleModule {

    @Binds
    abstract fun bindPeopleRepository(repository: PeopleRepository): IPeopleRepository

    @Binds
    abstract fun bindPeopleDetailRepository(repository: PeopleDetailRepository): IPeopleDetailRepository


}