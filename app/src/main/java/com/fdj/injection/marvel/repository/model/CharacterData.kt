package com.fdj.injection.marvel.repository.model

import com.google.gson.annotations.SerializedName

data class CharacterData(

    @field:SerializedName("thumbnail")
    val thumbnail: ThumbnailData? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,
)