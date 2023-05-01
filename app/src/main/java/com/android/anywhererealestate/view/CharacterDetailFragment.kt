package com.android.anywhererealestate.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.anywhererealestate.databinding.FragmentCharacterDetailBinding
import com.android.anywhererealestate.model.RelatedTopic
import com.android.anywhererealestate.util.toName
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val character: RelatedTopic by lazy {
        requireArguments().getParcelable<RelatedTopic>(ARG_CHARACTER)!!
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        binding.characterDescription.text = character.text
        Picasso.get().load(character.icon.url).into(binding.characterPicture)
        binding.characterDetailAppBar.title = character.toName()
        return binding.root
    }


    companion object {
        const val ARG_CHARACTER = "character"
    }
}