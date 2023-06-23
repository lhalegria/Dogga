package dev.lhalegria.dogga

import android.app.Application
import dev.lhalegria.dogga.datasource.di.dataSourceModule
import dev.lhalegria.dogga.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoggaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DoggaApp)
            modules(listOf(dataSourceModule, mainModule))
        }
    }
}
