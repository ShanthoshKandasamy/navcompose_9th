package com.bank.navcompose.network.model.planets

import com.bank.navcompose.data.model.Planets
import com.bank.navcompose.data.util.DomainMapper

class ResultToPlanetsMapper : DomainMapper<Result, Planets> {

    override fun mapToDomainModel(model: Result): Planets {
        return Planets(
            climate = model.climate,
            created = model.created,
            diameter = model.diameter,
            edited = model.edited,
            films = model.films.orEmpty(),
            gravity = model.gravity,
            name = model.name,
            orbital_period = model.orbital_period,
            population = model.population,
            residents = model.residents.orEmpty(),
            rotation_period = model.rotation_period,
            surface_water = model.surface_water,
            terrain = model.terrain,
            url = model.url
        )
    }

    override fun mapFromDomainModel(domainModel: Planets): Result {
        return Result(
            climate = domainModel.climate,
            created = domainModel.created,
            edited = domainModel.edited,
            films = domainModel.films,
            gravity = domainModel.gravity,
            name = domainModel.name,
            orbital_period = domainModel.orbital_period,
            population = domainModel.population,
            residents = domainModel.residents,
            rotation_period = domainModel.rotation_period,
            surface_water = domainModel.surface_water,
            terrain = domainModel.terrain,
            url = domainModel.url
        )
    }

    fun toDomainList(initial: List<Result>): List<Planets>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Planets>): List<Result>{
        return initial.map { mapFromDomainModel(it) }
    }


}