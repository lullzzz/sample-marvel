package com.lullzzzz.samplemarvel.data.model.event

import com.squareup.moshi.Json

data class EventSummary(
    @field:Json(name = "resourceURI")
    val resourceURI: String?,
    @field:Json(name = "name")
    val name: String?,
)
