package com.lullzzzz.samplemarvel.data.model.character

import com.lullzzzz.samplemarvel.data.model.*
import com.lullzzzz.samplemarvel.data.model.comic.ComicList
import com.lullzzzz.samplemarvel.data.model.event.EventList
import com.lullzzzz.samplemarvel.data.model.series.SeriesList
import com.lullzzzz.samplemarvel.data.model.story.StoryList
import com.squareup.moshi.Json

data class ComicCharacter(
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "modified")
    val modified: String? = null,
    @field:Json(name = "resourceUri")
    val resourceUri: String? = null,
    @field:Json(name = "urls")
    val urls: List<DataUrl>? = null,
    @field:Json(name = "thumbnail")
    val thumbnail: DataImage? = null,
    @field:Json(name = "comics")
    val comics: ComicList? = null,
    @field:Json(name = "stories")
    val stories: StoryList? = null,
    @field:Json(name = "events")
    val events: EventList? = null,
    @field:Json(name = "series")
    val series: SeriesList? = null,
)
