package com.fdj.injection.marvel.repository.model

import com.google.gson.annotations.SerializedName

data class CharacterResponseData(

    @field:SerializedName("data")
    val data: ContentData? = null,

    @field:SerializedName("status")
    val status: String? = null
)
