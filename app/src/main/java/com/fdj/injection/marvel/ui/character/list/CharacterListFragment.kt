package com.fdj.injection.marvel.ui.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.SimpleItemAnimator
import com.fdj.injection.R
import com.fdj.injection.databinding.FragmentCharactersListBinding
import com.fdj.injection.marvel.ui.character.detail.CharacterDetailFragment
import com.fdj.injection.utils.ui.UiRequestState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val characterListViewModel : CharacterListViewModel by viewModels()

    private lateinit var binding: FragmentCharactersListBinding

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = characterListViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterListAdapter =
            CharacterListAdapter(requireContext()) {
                findNavController().navigate(
                    R.id.action_CharacterListFragment_to_CharacterDetailFragment,
                    bundleOf(
                        CharacterDetailFragment.CHARACTER_ID to it.characterId
                    )
                )
            }

        binding.characterRecycleView.adapter = characterListAdapter

        val itemAnimator = binding.characterRecycleView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false
        binding.characterRecycleView.itemAnimator = itemAnimator

        initViewModel(characterListAdapter)
    }


    private fun initViewModel(characterListAdapter: CharacterListAdapter) {

        characterListViewModel.characters.observe(viewLifecycleOwner) { news ->
            characterListAdapter.submitList(news)
        }

        characterListViewModel.updateUiStateUi.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiRequestState.ERROR -> {
                    Snackbar.make(
                        binding.characterRecycleView,
                        resources.getString(R.string.error_data),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                else -> {
                    // Nothing to do , already handled with binding
                }
            }
        }
    }
}