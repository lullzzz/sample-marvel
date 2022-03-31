package com.lullzzzz.samplemarvel.data.model.comic

import com.squareup.moshi.Json

data class ComicSummary(
    @field:Json(name = "resourceURI")
    val resourceURI: String? = null,
    @field:Json(name = "name")
    val name: String? = null,
)
