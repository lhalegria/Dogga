package dev.lhalegria.dogga.datasource.di

import dev.lhalegria.dogga.datasource.network.retrofitApi
import dev.lhalegria.dogga.datasource.service.BreedsService
import org.koin.dsl.module

val dataSourceModule = module {

    factory { retrofitApi }
    factory { retrofitApi.create(BreedsService::class.java) }
}
