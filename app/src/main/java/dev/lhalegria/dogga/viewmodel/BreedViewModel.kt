package dev.lhalegria.dogga.viewmodel

import androidx.lifecycle.ViewModel
import dev.lhalegria.dogga.repository.IBreedRepository

class BreedViewModel(
    private val repository: IBreedRepository
): ViewModel() {

}
