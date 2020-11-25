package com.example.urbandictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionary.data.Definition

class DefinitionAdapter : RecyclerView.Adapter<DefinitionAdapter.DefinitionVH>() {

    class DefinitionVH(parentView: View) : RecyclerView.ViewHolder(parentView) {
        var txtDefinition: TextView = parentView.findViewById(R.id.prg_definition)
        var txtUpvotes: TextView = parentView.findViewById(R.id.txt_upvotes)
        var txtDownvotes: TextView = parentView.findViewById(R.id.txt_downvotes)
        var txtAuthor: TextView = parentView.findViewById(R.id.txt_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      DefinitionVH(LayoutInflater.from(parent.context).inflate(R.layout.definition_item, parent, false))

    override fun getItemCount() = diff.currentList.size

    override fun onBindViewHolder(holder: DefinitionVH, position: Int) {
        holder.txtDefinition.text = diff.currentList[position].definition
        holder.txtUpvotes.text = diff.currentList[position].thumbs_up.toString()
        holder.txtDownvotes.text = diff.currentList[position].thumbs_down.toString()
        holder.txtAuthor.text = diff.currentList[position].author
    }

    private val callback = object : DiffUtil.ItemCallback<Definition>(){
        override fun areItemsTheSame(oldItem: Definition, newItem: Definition) = false //oldItem.defid == newItem.defid

        override fun areContentsTheSame(oldItem: Definition, newItem: Definition) = false //oldItem.definition == newItem.definition
    }

    val diff = AsyncListDiffer<Definition>(this, callback)

}
