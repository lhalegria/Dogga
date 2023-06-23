package dev.lhalegria.dogga.di

import dev.lhalegria.dogga.model.mapper.BreedsMapper
import dev.lhalegria.dogga.model.mapper.IBreedMapper
import dev.lhalegria.dogga.repository.BreedRepository
import dev.lhalegria.dogga.repository.IBreedRepository
import dev.lhalegria.dogga.viewmodel.BreedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single<IBreedMapper> { BreedsMapper() }

    factory<IBreedRepository> {
        BreedRepository(service = get(), mapper = get())
    }

    viewModel {
        BreedViewModel(
            repository = get()
        )
    }
}
