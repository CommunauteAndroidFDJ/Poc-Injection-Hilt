package com.fdj.injection.marvel.ui.character.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.fdj.injection.marvel.ui.character.detail.CharacterDetailFragment
import com.fdj.injection.marvel.ui.character.list.CharacterListFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment = when (className) {
        CharacterListFragment::class.java.name -> CharacterListFragment()
        CharacterDetailFragment::class.java.name -> CharacterDetailFragment()

        else -> super.instantiate(classLoader, className)
    }
}