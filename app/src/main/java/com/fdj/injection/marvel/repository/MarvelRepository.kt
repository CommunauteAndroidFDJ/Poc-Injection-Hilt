package com.fdj.injection.marvel.repository

import com.fdj.injection.base.data.BaseRepository
import com.fdj.injection.base.data.BaseResponse
import com.fdj.injection.marvel.business.model.MarvelCharacter
import com.fdj.injection.marvel.repository.mapper.CharactersMapper
import javax.inject.Inject
import javax.inject.Named

class MarvelRepository @Inject constructor(
    private val marvelApi: MarvelApi
) : BaseRepository() {

    suspend fun getAllCharacters(orderBy: String): BaseResponse<List<MarvelCharacter>?> {
        return request({
            marvelApi.getAllCharacters(orderBy)
        }, { response ->
            CharactersMapper.mapAllCharacters(response)
        })
    }

    suspend fun getCharacterDetail(characterId: String): BaseResponse<MarvelCharacter?> {
        return request({
            marvelApi.getCharacterDetail(characterId)
        }, { response ->
            CharactersMapper.mapCharacterDetail(response)
        })
    }

}