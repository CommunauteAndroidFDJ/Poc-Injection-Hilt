package com.fdj.injection.marvel.ui.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fdj.injection.R
import com.fdj.injection.databinding.FragmentCharacterDetailsBinding
import com.fdj.injection.utils.ui.UiRequestState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private val characterViewModel : CharacterDetailViewModel by viewModels()


    private lateinit var binding: FragmentCharacterDetailsBinding

    companion object {
        const val CHARACTER_ID = "CHARACTER_ID"
    }

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = characterViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        initViewModel()

        arguments?.getInt(CHARACTER_ID)?.let {
            characterViewModel.showCharacterDetail(it.toString())
        } ?: run {
            showErrorMessage()
        }

    }

    private fun initViewModel() {

        characterViewModel.character.observe(viewLifecycleOwner) { character ->
            binding.item = character
        }

        characterViewModel.updateUiStateUi.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiRequestState.ERROR -> {
                    showErrorMessage()
                }
                else -> {
                    // Nothing to do , already handled with binding
                }
            }
        }
    }

    private fun showErrorMessage() {
        Snackbar.make(
            binding.root,
            resources.getString(R.string.error_data),
            Snackbar.LENGTH_LONG
        ).show()
    }

}
