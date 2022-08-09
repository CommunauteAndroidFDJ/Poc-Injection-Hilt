package com.fdj.injection.marvel.repository.model

import com.google.gson.annotations.SerializedName

data class ContentData(

    @field:SerializedName("results")
    val results: List<CharacterData?>? = null
)