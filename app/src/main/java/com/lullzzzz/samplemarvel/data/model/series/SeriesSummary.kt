package com.lullzzzz.samplemarvel.data.model.series

import com.squareup.moshi.Json

data class SeriesSummary(
    @field:Json(name = "resourceURI")
    val resourceURI: String?,
    @field:Json(name = "name")
    val name: String?,
)
