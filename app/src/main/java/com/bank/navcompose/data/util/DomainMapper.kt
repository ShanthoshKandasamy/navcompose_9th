package com.bank.navcompose.data.util

interface DomainMapper <T, DomainModel>{

    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}