package com.example.urbandictionary.api

import android.util.Log
import com.example.urbandictionary.data.Definition
import com.example.urbandictionary.data.DefintionResponse

class Repository {

    suspend fun searchTerm(term : String) : DefintionResponse<Definition>? {

        if(term.isEmpty()){
            throw Exception("Introduce a term to be found")
        }

        var listDefinitions : DefintionResponse<Definition>? = null
        try {
            val response = WebService.words.searchTerm(term)
            if(response.isSuccessful){
                listDefinitions = response.body()
            }else if (response.errorBody() != null){
                Log.e("RMC", "error ${response.errorBody()}")
            }
        }catch(e : Exception){
            Log.i("RMC", "exception")
        }finally {
            return listDefinitions
        }

    }

}