package com.example.urbandictionary

import android.app.Application
import com.example.urbandictionary.activities.DefinitionViewModel
import com.example.urbandictionary.api.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
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

    val moduleOne = module{
        single { Repository() }
        viewModel { DefinitionViewModel() }
    }

}