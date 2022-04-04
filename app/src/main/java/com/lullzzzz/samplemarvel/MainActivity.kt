package com.lullzzzz.samplemarvel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lullzzzz.samplemarvel.data.repository.MarvelRepository
import com.lullzzzz.samplemarvel.ui.theme.SampleMarvelTheme
import com.lullzzzz.samplemarvel.viewmodel.ComicCharacterViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivity : ComponentActivity() {
    val rep = MarvelApplication.repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            SampleMarvelTheme {
                val charModel: ComicCharacterViewModel = viewModel()
                val dataExample = charModel.name.observeAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(dataExample.value)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getName()
    }

    fun getName() {
        rep.fetchCharactersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val charModel by viewModels<ComicCharacterViewModel>()
                val char = it.data?.results?.map { character -> character.name }
                charModel.setName(char)
            },
                {
                    Log.e("hghghghg", it.message ?: "fffffffuuuuu")
                })
    }

}


@Composable
fun Greeting(name: List<String?>?) {
    val rep = MarvelApplication.repository
    Column() {
        name?.let {
            for (n in it) {
                Card(
                    modifier = Modifier.background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(3.dp)
                    )
                ) {
                    Text(
                        text = "Hello ${n}!",
                        modifier = Modifier.height(20.dp),
                        color = Color.Cyan
                    )
                    /*Button(
                        modifier = Modifier.background(color = Color.Green),
                        onClick = {
                            rep.
                        }
                    ){
                        Text(text = "Open details")
                    }*/
                }
            }
        }
        Button(
            modifier = Modifier.background(color = Color.Green),
            onClick = {
                rep.
            }
        ){
            Text(text = "Open details")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleMarvelTheme {
        Greeting(listOf("Android"))
    }
}