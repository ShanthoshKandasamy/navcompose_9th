package com.bank.navcompose.ui.people

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bank.navcompose.data.model.People
import com.bank.navcompose.network.model.people.ResultToPeopleMapper
import com.bank.navcompose.network.response.PeopleResponse
import com.bank.navcompose.repository.IPeopleRepository
import com.bank.navcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: IPeopleRepository,
    private val mapper: ResultToPeopleMapper
): ViewModel() {

    private val _people = MutableLiveData<PeopleUiState>()
    val people: LiveData<PeopleUiState>
        get() = _people

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    init{
        fetchPeople()
    }

    private fun fetchPeople() {
        viewModelScope.launch {
            repository.getPeoples().collect {
                when(it){
                    is Resource.Error -> {
                        Log.d("TAG", "")
                        _people.value = PeopleUiState.Error(msg = it.message ?: "Unknown error")
                    }
                    is Resource.Loading ->{
                        _isLoading.value = true
                        _people.value = PeopleUiState.Loading
                    }
                    is Resource.Success -> {
                        it.data?.let {
                            try {
                                _isLoading.value = false
                                val listOfPeople = transform(it)
                                _people.postValue(PeopleUiState.Success(peoples = listOfPeople))
                            } catch (e: Exception) {
                                _people.value = PeopleUiState.Error(msg = "Unknown error")
                            }
                        }
                    }
                }
            }
        }
    }

    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }

    private fun transform(apiResponse: PeopleResponse): List<People> {
        return mapper.toDomainList(apiResponse.results)
    }

    override fun onCleared() {
        super.onCleared()
        clear()
    }

    private fun clear() {
    }

    companion object {
        const val TAG = "PeopleViewModel"
    }

    sealed class PeopleUiState{
        data class Success(val peoples: List<People>): PeopleUiState()
        data class Error(val msg: String) : PeopleUiState()
        object Loading: PeopleUiState()
    }
}
