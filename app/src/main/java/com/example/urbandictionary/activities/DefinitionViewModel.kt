package com.example.urbandictionary.activities

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urbandictionary.api.IRepository
import com.example.urbandictionary.api.Repository
import com.example.urbandictionary.data.Definition
import kotlinx.coroutines.launch

class DefinitionViewModel(private val repository  : Repository) : ViewModel() {

    var definitionsListLD = MutableLiveData<List<Definition>>()
    var loadingLD = MutableLiveData<Boolean>()
    private var listNoSorted : List<Definition>? = null

    init {
        definitionsListLD.value = mutableListOf()
        loadingLD.value = false
    }

    fun searchTerm(term : String, sort : String = "") = viewModelScope.launch{
        try{
            loadingLD.value = true
            definitionsListLD.value = repository.searchTerm(term)?.list
            listNoSorted = definitionsListLD.value?.toMutableList()
        }catch(exc : Exception){
            Log.i("RMC", "Error!!")
        }finally {
            loadingLD.value = false
        }
    }

    fun sortByDownvotes(){
        definitionsListLD.value = definitionsListLD.value?.sortedByDescending { it.thumbs_down }
    }

    fun sortByUpvotes(){
        definitionsListLD.value = definitionsListLD.value?.sortedByDescending { it.thumbs_up }
    }

    fun originalList(){
        definitionsListLD.value = listNoSorted
    }


}