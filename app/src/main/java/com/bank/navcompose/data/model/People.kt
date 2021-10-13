package com.bank.navcompose.data.model

data class People(
    val birth_year: String? = null,
    val created: String? = null,
    val edited: String? = null,
    val eye_color: String? = null,
    val films: List<String> = listOf(),
    val gender: String? = null,
    val hair_color: String? = null,
    val height: String? = null,
    val homeworld: String? = null,
    val mass: String? = null,
    val name: String? = null,
    val skin_color: String? = null,
    val species: List<String> = listOf(),
    val starships: List<String> = listOf(),
    val url: String? = null,
    val vehicles: List<String> = listOf()
){
    companion object{
        fun mock() = People(
            /*id =0,*/
            birth_year= "19BBY",
            created= "2014-12-09T13:50:51.644000Z",
            edited = "2014-12-20T21:17:56.891000Z",
            eye_color = "blue",
            films = listOf("https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/2/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/6/"),
            gender = "male",
            hair_color = "blond",
            height = "172",
            homeworld = "https://swapi.dev/api/planets/1/",
            mass = "77",
            name = "Luke Skywalker",
            skin_color = "fair",
            species = listOf(),
            starships = listOf("https://swapi.dev/api/starships/12/",
                "https://swapi.dev/api/starships/22/"),
            url = "https://swapi.dev/api/people/1/",
            vehicles = listOf("https://swapi.dev/api/vehicles/14/",
                "https://swapi.dev/api/vehicles/30/"))
    }
}