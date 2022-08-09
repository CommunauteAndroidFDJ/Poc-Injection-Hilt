package com.fdj.injection.marvel.repository.model

import com.google.gson.annotations.SerializedName

data class ThumbnailData(

    @field:SerializedName("path")
    val path: String? = null,

    @field:SerializedName("extension")
    val extension: String? = null
)