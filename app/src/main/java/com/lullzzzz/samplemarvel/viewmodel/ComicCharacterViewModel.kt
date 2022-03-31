package com.lullzzzz.samplemarvel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComicCharacterViewModel :ViewModel() {
    val name = MutableLiveData<List<String?>?>()


    fun setName(charName: List<String?>?){
        name.value = charName
    }
}