package com.lullzzzz.samplemarvel

import android.app.Application
import com.lullzzzz.samplemarvel.data.repository.MarvelRepository

class MarvelApplication: Application() {
    companion object{
        val repository = MarvelRepository()
    }
}