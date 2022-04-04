package com.lullzzzz.samplemarvel.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lullzzzz.samplemarvel.data.model.character.CharacterDataContainer
import com.lullzzzz.samplemarvel.data.model.character.ComicCharacter
import com.lullzzzz.samplemarvel.data.repository.MarvelRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ComicCharacterViewModel(private val repository: MarvelRepository) : ViewModel() {
    val detail = MutableLiveData<ComicCharacter?>()
    val characterList = MutableLiveData<CharacterDataContainer?>()
    val nextAvailable = MutableLiveData(false)
    val prevAvailable = MutableLiveData(false)
    val attributeText = MutableLiveData("")

    init {
        fetchData()
    }

    fun fetchNextData() {
        val offset = characterList.value?.offset ?: 0
        val showed = characterList.value?.count ?: 0
        fetchData(offset + showed)
    }

    fun fetchPrevData() {
        val offset = characterList.value?.offset ?: 0
        val showed = characterList.value?.count ?: 0
        fetchData(offset - showed)
    }

    fun fetchData(offset: Int = 0) {
        nextAvailable.value = false
        prevAvailable.value = false
        repository.fetchCharactersList(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                characterList.value = it.data
                attributeText.value = it.attributionText
                it.data?.let { data ->
                    val total = data.total ?: 0
                    val showed = (data.count ?: 0) + (data.offset ?: 0)
                    nextAvailable.value = total > showed
                    prevAvailable.value = data.offset ?: 0 > 0
                }

            },
                {
                    Log.e("Character view model", it.message ?: "Unknown Error")
                })
    }
}