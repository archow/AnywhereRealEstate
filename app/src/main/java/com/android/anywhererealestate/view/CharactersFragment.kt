package com.android.anywhererealestate.view

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.android.anywhererealestate.BuildConfig
import com.android.anywhererealestate.R
import com.android.anywhererealestate.databinding.FragmentCharactersBinding
import com.android.anywhererealestate.model.ResultOf
import com.android.anywhererealestate.repo.CharactersRepository
import com.android.anywhererealestate.util.ApiException
import com.android.anywhererealestate.util.NoInternetException
import com.android.anywhererealestate.viewmodel.CharactersViewModel
import com.google.android.material.snackbar.Snackbar

class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels {CharactersViewModel.Factory}
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.countriesListView
        progressBar = binding.progressBar

        adapter = CharacterListAdapter()
        recyclerView.adapter = adapter

        viewModel.getCharactersLiveData().observe(viewLifecycleOwner
        ) {
            when (it) {
                is ResultOf.Success -> {
                    adapter.submitList(it.value)
                    removeProgressbar()
                }
                is ResultOf.Failure -> {
                    onFailure(it.exception.message.toString())
                }
            }

        }

        displayProgressbar()
        viewModel.getCharactersFromNetwork(BuildConfig.API_QUERY)
    }

    private fun displayProgressbar() {
        if (!progressBar.isVisible) {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun removeProgressbar() {
        if (progressBar.isVisible) {
            progressBar.visibility = View.GONE
        }
    }

    private fun onFailure(message: String) {
        val parentLayout = this.binding.containerView
        removeProgressbar()
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()
    }
}