package com.example.urbandictionary.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionary.DefinitionAdapter
import com.example.urbandictionary.R
import com.example.urbandictionary.databinding.ActivityMainBinding
import com.example.urbandictionary.fragments.OrderBottomSheet
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var  definitionAdapter = DefinitionAdapter()

    private val definitionsViewModel : DefinitionViewModel by viewModel()

    private lateinit var binding : ActivityMainBinding

    private var orderBottomSheet = OrderBottomSheet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.definitionVM = definitionsViewModel

        sv_term.isIconified = false
        sv_term.requestFocus()

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

        btn_order_by.setOnClickListener {
            orderBottomSheet.apply {
                show(supportFragmentManager,"")
            }
        }

        sv_term.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            definitionsViewModel.searchTerm(it)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}