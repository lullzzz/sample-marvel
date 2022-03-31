package com.lullzzzz.samplemarvel.data.model.story

import com.squareup.moshi.Json

data class StorySummary(
    @field:Json(name = "resourceURI")
    val resourceURI: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "type")
    val type: String?,
)
