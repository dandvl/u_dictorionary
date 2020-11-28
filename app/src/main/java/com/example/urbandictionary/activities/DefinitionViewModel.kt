package com.example.urbandictionary.activities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urbandictionary.api.IRepository
import com.example.urbandictionary.api.Repository
import com.example.urbandictionary.data.Definition
import kotlinx.coroutines.launch

class DefinitionViewModel(private val repository  : Repository) : ViewModel() {

    private var _definitionsListLD = MutableLiveData<List<Definition>>()
    var definitionsListLD : LiveData<List<Definition>> = _definitionsListLD

    private var _loadingLD = MutableLiveData<Boolean>()
    var loadingLD : LiveData<Boolean> = _loadingLD

    private var listNoSorted : List<Definition>? = null

    init {
        _definitionsListLD.value = mutableListOf()
        _loadingLD.value = false
    }

    fun searchTerm(term : String) = viewModelScope.launch{
        try{
            _loadingLD.value = true
            _definitionsListLD.value = repository.searchTerm(term)?.list
            listNoSorted = _definitionsListLD.value?.toMutableList()
        }catch(exc : Exception){
            Log.i("RMC", "Error!!")
        }finally {
            _loadingLD.value = false
        }
    }

    fun sortByDownvotes(){
        _definitionsListLD.value = _definitionsListLD.value?.sortedByDescending { it.thumbs_down }
    }

    fun sortByUpvotes(){
        _definitionsListLD.value = _definitionsListLD.value?.sortedByDescending { it.thumbs_up }
    }

    fun originalList(){
        _definitionsListLD.value = listNoSorted
    }


}