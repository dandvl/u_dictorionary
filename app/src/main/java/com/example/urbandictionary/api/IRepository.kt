package com.example.urbandictionary.api

import com.example.urbandictionary.data.Definition
import com.example.urbandictionary.data.DefintionResponse

interface IRepository {
    suspend fun searchTerm(term : String) : DefintionResponse<Definition>?
}