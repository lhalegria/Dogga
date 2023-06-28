package dev.lhalegria.dogga.datasource.di

import dev.lhalegria.dogga.datasource.network.retrofitApi
import dev.lhalegria.dogga.datasource.service.BreedService
import org.koin.dsl.module

val dataSourceModule = module {

    factory { retrofitApi }
    factory { retrofitApi.create(BreedService::class.java) }
}
