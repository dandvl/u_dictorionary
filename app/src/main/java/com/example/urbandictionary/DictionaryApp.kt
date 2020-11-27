package com.example.urbandictionary

import android.app.Application
import com.example.urbandictionary.activities.DefinitionViewModel
import com.example.urbandictionary.api.Repository
import com.example.urbandictionary.api.WebService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@DictionaryApp)
            modules(moduleOne)
        }

    }

    private val moduleOne = module{
        single { WebService }
        single { Repository(get()) }
        viewModel { DefinitionViewModel(get()) }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}