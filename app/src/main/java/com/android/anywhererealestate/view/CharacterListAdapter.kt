package com.android.anywhererealestate.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.android.anywhererealestate.databinding.CharacterItemRowBinding
import com.android.anywhererealestate.model.RelatedTopic

class CharacterListAdapter : ListAdapter<RelatedTopic, CharacterHolder>(CharacterDiffUtils) {

    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterItemRowBinding.inflate(inflater, parent, false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = currentList[position]
        holder.onBind(character)
    }
}

object CharacterDiffUtils : DiffUtil.ItemCallback<RelatedTopic>() {
    override fun areContentsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean =
        oldItem==newItem

    override fun areItemsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean =
        oldItem.firstURL==newItem.firstURL && oldItem.icon==newItem.icon &&
                oldItem.text==newItem.text
}