package com.lullzzzz.samplemarvel.data.repository

import android.util.Log
import com.lullzzzz.samplemarvel.data.model.character.CharacterDataWrapper
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class MarvelRepository() {
    private val api: MarvelApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(HashInterceptor())

        val rxAdapter: RxJava3CallAdapterFactory = RxJava3CallAdapterFactory.create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/")
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
        api = retrofit.create(MarvelApi::class.java)
    }


    fun fetchCharactersList(offset: Int = 0): Observable<CharacterDataWrapper> =
        api.listCharacters(offset).doOnError {
            Log.e("TAG", it.stackTraceToString())
        }
}