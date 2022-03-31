package com.lullzzzz.samplemarvel.data.model

import com.squareup.moshi.Json

data class DataImage(
    @field:Json(name = "path")
    val path: String?,
    @field:Json(name = "extension")
    val extension: String?
)
