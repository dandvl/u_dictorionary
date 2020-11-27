package com.example.urbandictionary

import com.example.urbandictionary.api.Repository
import com.example.urbandictionary.api.WebService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.IllegalArgumentException

class RepositoryTest : KoinTest {

//    private val SampleModule = module {
//        single { WebService }
//        single { Repository(get()) }
//
//        val mockedContext = mock(Context::class.java)
//
//
//        val koin = koinApplication {
//            androidContext(mockedContext)
//            modules(SampleModule)
//        }.koin
//
//    }


    @Test(expected = IllegalArgumentException::class)
    fun `empty String should throw exception`() = runBlocking {

        val webService: WebService = mock(WebService::class.java)

        val term = "hello"

//        `when`(webService.words.searchTerm(term)).thenReturn()

        val res = Repository(webService).searchTerm(term)
    }

}