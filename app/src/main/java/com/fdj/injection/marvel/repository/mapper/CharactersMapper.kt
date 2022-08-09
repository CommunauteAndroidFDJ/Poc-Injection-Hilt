package com.fdj.injection.marvel.repository.mapper

import com.fdj.injection.marvel.business.model.MarvelCharacter
import com.fdj.injection.marvel.repository.model.CharacterResponseData
import com.fdj.injection.marvel.repository.model.CharacterData

object CharactersMapper {

    fun mapAllCharacters(
        characterResponse: CharacterResponseData
    ): List<MarvelCharacter>? = characterResponse.data?.results?.map {
        mapCharacter(it)
    }

    fun mapCharacterDetail(
        characterResponse: CharacterResponseData
    ): MarvelCharacter? = characterResponse.data?.results?.firstOrNull()?.let {
        mapCharacter(it)
    }

    private fun mapCharacter(characterData: CharacterData?) = MarvelCharacter(
        name = characterData?.name,
        description = characterData?.description,
        thumbnail = characterData?.thumbnail?.let {
            it.path + "." + it.extension
        } ?: "",
        id = characterData?.id
    )
}