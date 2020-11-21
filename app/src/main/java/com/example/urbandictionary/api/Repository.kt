package com.example.urbandictionary.api

class Repository {

    suspend fun loadDefinitions() = WebService.words.findTerm("air")

}