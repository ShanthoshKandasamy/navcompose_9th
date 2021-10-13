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
import com.bank.navcompose.repository.IPeopleDetailRepository
import com.bank.navcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PeopleDetailViewModel @Inject constructor(
    private val repository: IPeopleDetailRepository
): ViewModel() {

    private val _peopleDetail = MutableLiveData<PeopleDetailUiState>()
    val peopleDetail: LiveData<PeopleDetailUiState>
        get() = _peopleDetail

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    init{
        fetchPeople()
    }

    private fun fetchPeople() {
        viewModelScope.launch {
            repository.getPeople(id =1).collect {
                when(it){
                    is Resource.Error -> {
                        Log.d("TAG", "")
                        _peopleDetail.value = PeopleDetailUiState.Error(msg = it.message ?: "Unknown error")
                    }
                    is Resource.Loading ->{
                        _isLoading.value = true
                        _peopleDetail.value = PeopleDetailUiState.Loading
                    }
                    is Resource.Success -> {
                        it.data?.let {
                            try {
                                _isLoading.value = false
                                _peopleDetail.postValue(PeopleDetailUiState.Success(people = it))
                            } catch (e: Exception) {
                                _peopleDetail.value = PeopleDetailUiState.Error(msg = "Unknown error")
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

    override fun onCleared() {
        super.onCleared()
        clear()
    }

    private fun clear() {
    }

    companion object {
        const val TAG = "PeopleDetailViewModel"
    }

    sealed class PeopleDetailUiState{
        data class Success(val people: People): PeopleDetailUiState()
        data class Error(val msg: String) : PeopleDetailUiState()
        object Loading: PeopleDetailUiState()
    }
}

/*
@HiltViewModel
class PeopleDetailViewModel @Inject constructor(
    private val repository: IPeopleDetailRepository
) : ViewModel() {

    private val peopleSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)
    val pepoleDetailsFlow = peopleSharedFlow.flatMapLatest {
        repository.getPeople(id =1)
    }

    @WorkerThread
    fun loadPosterById(id: Long) = peopleSharedFlow.tryEmit(id)
}*/

