package com.example.urbandictionary.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urbandictionary.data.Definition
import com.example.urbandictionary.api.Repository
import com.example.urbandictionary.data.HttpResponse
import kotlinx.coroutines.launch

class DefinitionViewModel(var repository: Repository) : ViewModel() {

    var definitionsLD = MutableLiveData<HttpResponse<Definition>>()

    init {
        loadDefinitions()
    }

    private fun loadDefinitions() = viewModelScope.launch{
        definitionsLD.value = repository.loadDefinitions()
    }

}