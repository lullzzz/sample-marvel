package com.lullzzzz.samplemarvel.data.model.comic

import com.squareup.moshi.Json

data class ComicList(
    @field:Json(name = "available")
    val available: Int? = null,
    @field:Json(name = "returned")
    val returned: Int? = null,
    @field:Json(name = "collectionURI")
    val collectionURI: String? = null,
    @field:Json(name = "items")
    val items: List<ComicSummary>? = null,
)
