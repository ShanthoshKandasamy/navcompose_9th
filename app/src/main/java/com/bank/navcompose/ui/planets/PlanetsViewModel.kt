package com.bank.navcompose.ui.planets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bank.navcompose.data.model.Planets
import com.bank.navcompose.repository.IPlanetRepository
import com.bank.navcompose.network.response.PlanetResponse
import com.bank.navcompose.network.model.planets.ResultToPlanetsMapper
import com.bank.navcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val repository: IPlanetRepository,
    private val mapper: ResultToPlanetsMapper
): ViewModel() {
    private val _planets = MutableLiveData<PlanetsUiState>()
    val planets: LiveData<PlanetsUiState>
        get() = _planets

    init{
        fetchPlanets()
    }

    /*
    private val _planets = MutableLiveData<Resource<List<Result>>>()
    val planets: LiveData<Resource<List<Result>>>
        get() = _planets

    init{
        fetchPlanets()
    }
    fun fetchPlanets() {
        viewModelScope.launch {
            repository.getPlanets().collect {
                when(it){
                    is Resource.Error -> _planets.postValue(Resource.Error(message = it.message ?: "Unknown error"))
                    is Resource.Loading -> _planets.postValue(Resource.Loading())
                    is Resource.Success -> {
                        it.data?.let {
                            try {
                                _planets.postValue(Resource.Success(data = transform(it)))
                            } catch (e: Exception) {
                                _planets.postValue(Resource.Error(message = "Date Parse Error"))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun transform(apiResponse: PeopleResponse): List<People> {
        return mapper.toDomainList(apiResponse.results)
    }*/

    fun fetchPlanets() {
        viewModelScope.launch {
            repository.getPlanets().collect {
                when(it){
                    is Resource.Error -> _planets.value = PlanetsUiState.Error(msg = it.message ?: "Unknown error")
                    is Resource.Loading -> _planets.value = PlanetsUiState.Loading
                    is Resource.Success -> {
                        it.data?.let {
                            try {
                                val listOfPeople = transform(it)
                                _planets.postValue(PlanetsUiState.Success(planets = listOfPeople))
                            } catch (e: Exception) {
                                _planets.postValue(PlanetsUiState.Error(msg = "Date Parse Error"))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun transform(apiResponse: PlanetResponse): List<Planets> {
        return mapper.toDomainList(apiResponse.results)
    }

    override fun onCleared() {
        super.onCleared()
        clear()
    }

    private fun clear() {
    }

    companion object {
        const val TAG = "PlanetsViewModel"
    }

    sealed class PlanetsUiState{
        data class Success(val planets: List<Planets>): PlanetsUiState()
        data class Error(val msg: String) : PlanetsUiState()
        object Loading: PlanetsUiState()
    }
}
