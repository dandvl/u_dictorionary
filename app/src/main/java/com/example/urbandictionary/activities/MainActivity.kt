package com.example.urbandictionary.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionary.DefinitionAdapter
import com.example.urbandictionary.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var  definitionAdapter = DefinitionAdapter()

    private val definitionsViewModel : DefinitionViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        definitionsViewModel.definitionsLD.observe(this, Observer { response ->
            if(response.list.isNotEmpty()) {
                definitionAdapter.diff.submitList(response.list)
            }
        })

        rv_words.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = definitionAdapter
        }



    }


}