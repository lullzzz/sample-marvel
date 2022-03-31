package com.lullzzzz.samplemarvel.data.model.event

import com.squareup.moshi.Json

data class EventList(
    @field:Json(name = "available")
    val available: Int?,
    @field:Json(name = "returned")
    val returned: Int?,
    @field:Json(name = "collectionURI")
    val collectionURI: String?,
    @field:Json(name = "items")
    val items: List<EventSummary>?,
)
