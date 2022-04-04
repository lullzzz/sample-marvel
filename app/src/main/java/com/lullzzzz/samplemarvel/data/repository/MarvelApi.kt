package com.lullzzzz.samplemarvel.data.repository

import com.lullzzzz.samplemarvel.data.model.character.CharacterDataWrapper
import com.lullzzzz.samplemarvel.data.model.Constants
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelApi {
    @GET("v1/public/characters")
    fun listCharacters(
        @Query("offset") offset: Int = 0,
        @Query("orderBy") orderBy: String = Constants.ORDER_BY_NAME
    ): Observable<CharacterDataWrapper>
}