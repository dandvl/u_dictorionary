package com.example.urbandictionary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.urbandictionary.R
import com.example.urbandictionary.activities.DefinitionViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.menu_bottom_sheet.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class OrderBottomSheet : BottomSheetDialogFragment() {

    private val definitionsViewModel : DefinitionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_order_default.setOnClickListener {
            definitionsViewModel.originalList()
        }
        btn_order_upvotes.setOnClickListener {
            definitionsViewModel.sortByUpvotes()
        }
        btn_order_downvotes.setOnClickListener {
            definitionsViewModel.sortByDownvotes()
        }
    }
}