package dev.lhalegria.dogga

import android.app.Application
import android.content.res.Resources
import dev.lhalegria.dogga.datasource.di.dataSourceModule
import dev.lhalegria.dogga.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoggaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appResources = this.resources
        startKoin {
            androidContext(this@DoggaApp)
            modules(listOf(dataSourceModule, mainModule))
        }
    }

    companion object {
        lateinit var appResources: Resources
            private set
    }
}
