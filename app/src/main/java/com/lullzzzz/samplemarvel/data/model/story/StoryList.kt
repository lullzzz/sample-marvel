package com.lullzzzz.samplemarvel.data.model.story

import com.squareup.moshi.Json

data class StoryList(
    @field:Json(name = "available")
    val available: Int?,
    @field:Json(name = "returned")
    val returned: Int?,
    @field:Json(name = "collectionURI")
    val collectionURI: String?,
    @field:Json(name = "items")
    val items: List<StorySummary>?,
)
