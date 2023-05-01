package com.android.anywhererealestate.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.anywhererealestate.R
import com.android.anywhererealestate.databinding.CharacterItemRowBinding
import com.android.anywhererealestate.model.RelatedTopic
import com.android.anywhererealestate.util.toName
import com.squareup.picasso.Picasso

class CharacterHolder(binding: CharacterItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
    private val characterImage : ImageView = binding.characterImage
    private val characterName : TextView = binding.characterName

    fun onBind(relatedTopic: RelatedTopic) {
        characterName.text = relatedTopic.toName()
        Picasso.get().load(relatedTopic.icon.url)
            .placeholder(R.drawable.placeholder)
            .into(characterImage)

        itemView.setOnClickListener { itemView ->
            val bundle = Bundle()

            bundle.putParcelable(
                CharacterDetailFragment.ARG_CHARACTER,
                relatedTopic
            )

            itemView.findNavController().navigate(R.id.show_character_detail, bundle)
        }
    }
}