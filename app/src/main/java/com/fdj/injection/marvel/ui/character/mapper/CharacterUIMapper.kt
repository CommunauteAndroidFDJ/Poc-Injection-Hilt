package com.fdj.injection.marvel.ui.character.mapper

import com.fdj.injection.marvel.business.model.MarvelCharacter
import com.fdj.injection.marvel.ui.character.model.CharacterModel

object CharacterUIMapper {

    fun mapCharacterList(characters: List<MarvelCharacter>?) =
        characters?.let { marvelCharacters ->
            marvelCharacters.map {
                mapCharacter(it)
            }
        } ?: emptyList()


    fun mapCharacter(marvelCharacter: MarvelCharacter) =
        CharacterModel(
            name = marvelCharacter.name ?: "",
            description = marvelCharacter.description ?: "",
            characterImage = marvelCharacter.thumbnail ?: "",
            characterId = marvelCharacter.id ?: 0
        )
}