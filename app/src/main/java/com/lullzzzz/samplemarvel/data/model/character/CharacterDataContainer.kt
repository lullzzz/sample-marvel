package com.lullzzzz.samplemarvel.data.model.character

import com.squareup.moshi.Json

class CharacterDataContainer(
    @field:Json(name = "offset")
    val offset: Int? = null,

    @field:Json(name = "limit")
    val limit: Int? = null,

    @field:Json(name = "total")
    val total: Int? = null,

    @field:Json(name = "count")
    val count: Int? = null,

    @field:Json(name = "results")
    val results: List<ComicCharacter>? = null
)
