package dev.lhalegria.dogga.di

import dev.lhalegria.dogga.model.mapper.BreedMapper
import dev.lhalegria.dogga.model.mapper.IBreedMapper
import dev.lhalegria.dogga.repository.BreedRepository
import dev.lhalegria.dogga.repository.IBreedRepository
import dev.lhalegria.dogga.viewmodel.BreedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single<IBreedMapper> { BreedMapper() }

    factory<IBreedRepository> {
        BreedRepository(service = get(), mapper = get())
    }

    viewModel {
        BreedViewModel(
            repository = get()
        )
    }
}
