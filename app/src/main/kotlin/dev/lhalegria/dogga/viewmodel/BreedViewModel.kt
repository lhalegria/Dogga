package dev.lhalegria.dogga.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lhalegria.dogga.model.BreedModel
import dev.lhalegria.dogga.repository.IBreedRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BreedViewModel(
    private val repository: IBreedRepository
): ViewModel() {

    private val _breedStateFlow = MutableStateFlow<RequestState<List<BreedModel>>>(RequestState.Loading)
    val breedStateFlow: StateFlow<RequestState<List<BreedModel>>>
        get() = _breedStateFlow

    private val _subBreedStateFlow = MutableStateFlow<RequestState<List<BreedModel>>>(RequestState.Loading)
    val subBreedStateFlow: StateFlow<RequestState<List<BreedModel>>>
        get() = _subBreedStateFlow

    private val _breedImageStateFlow = MutableStateFlow<RequestState<String>>(RequestState.Loading)
    val breedImageStateFlow: StateFlow<RequestState<String>>
        get() = _breedImageStateFlow

    fun getBreeds() = viewModelScope.launch(IO) {
        repository.getBreeds()
            .onSuccess {
                _breedStateFlow.value = RequestState.Success(it)
            }
            .onFailure {
                _breedStateFlow.value = RequestState.Error(it)
            }
    }

    fun getSubBreed(breed: String) = viewModelScope.launch(IO) {
        repository.getSubBreed(breed)
            .onSuccess {
                _subBreedStateFlow.value = RequestState.Success(it)
            }
            .onFailure {
                _subBreedStateFlow.value = RequestState.Error(it)
            }
    }

    fun getBreedImage(breed: String) = viewModelScope.launch(IO) {
        repository.getBreedImage(breed)
            .onSuccess {
                _breedImageStateFlow.emit(RequestState.Success(it))
            }
            .onFailure {
                _breedImageStateFlow.emit(RequestState.Error(it))
            }
    }
}
