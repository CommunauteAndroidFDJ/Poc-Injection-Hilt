package com.fdj.injection.marvel.repository

import com.fdj.injection.marvel.repository.model.CharacterResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("orderBy") orderBy: String
    ): Response<CharacterResponseData>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: String
    ): Response<CharacterResponseData>
}