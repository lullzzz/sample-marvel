package com.lullzzzz.samplemarvel.data.model.character

import com.squareup.moshi.Json

data class CharacterDataWrapper(
    @field:Json(name = "code")
    val code: Int? = null,

    @field:Json(name = "status")
    val status: String? = null,

    @field:Json(name = "copyright")
    val copyright: String? = null,

    @field:Json(name = "attributionText")
    val attributionText: String? = null,

    @field:Json(name = "attributionHTML")
    val attributionHTML: String? = null,

    @field:Json(name = "etag")
    val etag: String? = null,

    @field:Json(name = "data")
    val data: CharacterDataContainer? = null,
)