package com.example.urbandictionary.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionary.DefinitionAdapter
import com.example.urbandictionary.R
import com.example.urbandictionary.fragments.OrderBottomSheet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var  definitionAdapter = DefinitionAdapter()

    private lateinit var definitionsViewModel : DefinitionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        definitionsViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)

        definitionsViewModel.definitionsListLD.observe(this, Observer { definitionsList ->
            if(definitionsList?.isNotEmpty() == true) {
                definitionAdapter.diff.submitList(definitionsList)
            }else{

            }
        })

        rv_words.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = definitionAdapter
        }

        btn_search.setOnClickListener {
            definitionsViewModel.searchTerm(edt_term.text.toString())
        }

        btn_order_by.setOnClickListener {
            OrderBottomSheet().apply {
                show(supportFragmentManager,"")
            }
        }

    }

}