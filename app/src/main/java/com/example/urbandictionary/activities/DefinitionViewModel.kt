package com.example.urbandictionary.activities

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urbandictionary.data.Definition
import com.example.urbandictionary.api.Repository
import kotlinx.coroutines.launch

class DefinitionViewModel() : ViewModel() {

    private val repository = Repository()

    var definitionsListLD = MutableLiveData<List<Definition>>()

    fun searchTerm(term : String, sort : String = "") = viewModelScope.launch{

        try{
            definitionsListLD.value = repository.searchTerm(term)?.list
        } catch(exc : Exception){
            Log.i("RMC", "Error!!")
        }
    }

    fun sortByDownvotes(){
        definitionsListLD.value = definitionsListLD.value?.sortedByDescending { it.thumbs_down }
    }

    fun sortByUpvotes(){
        definitionsListLD.value = definitionsListLD.value?.sortedByDescending { it.thumbs_up }
    }


}