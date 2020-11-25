package com.example.urbandictionary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.urbandictionary.R
import com.example.urbandictionary.activities.DefinitionViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.menu_bottom_sheet.*

class OrderBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var definitionsViewModel : DefinitionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            definitionsViewModel = ViewModelProviders.of(it).get(DefinitionViewModel::class.java)
        }

        btn_order_default.setOnClickListener {
            definitionsViewModel.sortByDownvotes()
        }
        btn_order_upvotes.setOnClickListener {
            definitionsViewModel.sortByUpvotes()
        }
        btn_order_downvotes.setOnClickListener {
        }
    }
}