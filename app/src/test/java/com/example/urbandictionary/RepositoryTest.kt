package com.example.urbandictionary

import com.example.urbandictionary.api.Repository
import com.example.urbandictionary.api.WebService
import com.example.urbandictionary.api.WordService
import com.example.urbandictionary.data.Definition
import com.example.urbandictionary.data.DefintionResponse
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.io.BufferedReader
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @Mock
    private lateinit var webService : WebService

    @Mock
    private lateinit var words : WordService

    @Test(expected = IllegalArgumentException::class)
    fun `empty String should throw exception`() = runBlocking<Unit> {
        val fakeWebService = mock(WebService::class.java)
        Repository(fakeWebService).searchTerm("")
    }

    @Test
    fun `term String should retrieve results`() = runBlocking {
        val term = "air"

        val jsonPath = javaClass.classLoader?.getResource("fakeTerms.json")?.path
        val jsonBuffered: BufferedReader = File(jsonPath!!).bufferedReader()
        val jsonStr = jsonBuffered.use { it.readText() }

        val collectionType = object : TypeToken<DefintionResponse<Definition>>() {}.type
        val fakeResponse: DefintionResponse<Definition> = GsonBuilder().create().fromJson(jsonStr, collectionType)
        
        val retrofitResponse = Response.success(fakeResponse)

        `when`(webService.words).thenReturn(words)
        `when`(words.searchTerm(term)).thenReturn(retrofitResponse)
        val res = Repository(webService).searchTerm(term)

        assertEquals(2, res?.list?.size)
    }

}