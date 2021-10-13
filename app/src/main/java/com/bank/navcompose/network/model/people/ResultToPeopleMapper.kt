package com.bank.navcompose.network.model.people

import com.bank.navcompose.data.model.People
import com.bank.navcompose.data.util.DomainMapper

class ResultToPeopleMapper : DomainMapper<Result, People> {

    override fun mapToDomainModel(model: Result): People {
        return People(
            birth_year = model.birth_year,
            created = model.created,
            edited = model.edited,
            eye_color = model.eye_color,
            films = model.films.orEmpty(),
            gender = model.gender,
            hair_color = model.hair_color,
            height = model.height,
            homeworld= model.homeworld,
            mass = model.mass,
            name = model.name,
            skin_color = model.skin_color,
            species = model.species.orEmpty(),
            starships = model.starships.orEmpty(),
            url = model.url,
            vehicles = model.vehicles.orEmpty()
        )
    }

    override fun mapFromDomainModel(domainModel: People): Result {
        return Result(
            birth_year = domainModel.birth_year,
            created = domainModel.created,
            edited = domainModel.edited,
            eye_color = domainModel.eye_color,
            films = domainModel.films,
            gender = domainModel.gender,
            hair_color = domainModel.hair_color,
            height = domainModel.height,
            homeworld= domainModel.homeworld,
            mass = domainModel.mass,
            name = domainModel.name,
            skin_color = domainModel.skin_color,
            species = domainModel.species,
            starships = domainModel.starships,
            url = domainModel.url,
            vehicles = domainModel.vehicles
        )
    }

    fun toDomainList(initial: List<Result>): List<People>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<People>): List<Result>{
        return initial.map { mapFromDomainModel(it) }
    }


}
