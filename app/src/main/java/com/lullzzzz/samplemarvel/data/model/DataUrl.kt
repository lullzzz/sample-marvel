package com.lullzzzz.samplemarvel.data.model

import com.squareup.moshi.Json

data class DataUrl(
    @field:Json(name = "type")
    val type: String?,
    @field:Json(name = "url")
    val url: String?
)
