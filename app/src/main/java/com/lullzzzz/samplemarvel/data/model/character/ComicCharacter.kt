package com.lullzzzz.samplemarvel.data.model.character

import com.lullzzzz.samplemarvel.data.model.*
import com.lullzzzz.samplemarvel.data.model.comic.ComicList
import com.lullzzzz.samplemarvel.data.model.event.EventList
import com.lullzzzz.samplemarvel.data.model.series.SeriesList
import com.lullzzzz.samplemarvel.data.model.story.StoryList
import com.squareup.moshi.Json

data class ComicCharacter(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "modified")
    val modified: String?,
    @field:Json(name = "resourceUri")
    val resourceUri: String?,
    @field:Json(name = "urls")
    val urls: List<DataUrl>?,
    @field:Json(name = "thumbnail")
    val thumbnail: DataImage?,
    @field:Json(name = "comics")
    val comics: ComicList?,
    @field:Json(name = "stories")
    val stories: StoryList?,
    @field:Json(name = "events")
    val events: EventList?,
    @field:Json(name = "series")
    val series: SeriesList?,
)
