package com.example.urbandictionary.api

import com.example.urbandictionary.data.Definition
import com.example.urbandictionary.data.DefintionResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface WordService{

    @Headers("x-rapidapi-key:4e8ac41cbcmsh88f87a280b8645ap195422jsn388adcc0e397")
    @GET("define")
    suspend fun searchTerm(@Query("term") term : String) : Response<DefintionResponse<Definition>>

}

object WebService {
    val words: WordService by lazy {  retrofit.create(WordService::class.java) }
}