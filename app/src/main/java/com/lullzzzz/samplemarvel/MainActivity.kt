package com.lullzzzz.samplemarvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lullzzzz.samplemarvel.ui.CharacterDetail
import com.lullzzzz.samplemarvel.ui.MarvelCharacterList
import com.lullzzzz.samplemarvel.ui.theme.Purple200
import com.lullzzzz.samplemarvel.ui.theme.SampleMarvelTheme
import com.lullzzzz.samplemarvel.viewmodel.ComicCharacterViewModel


class MainActivity : ComponentActivity() {
    private val rep = MarvelApplication.repository
    private val charModel: ComicCharacterViewModel = ComicCharacterViewModel(rep)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleMarvelTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main(charModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        charModel.fetchData()
    }
}

@Composable
fun Main(charModel: ComicCharacterViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "characterList") {
        composable("characterList") { MarvelCharacterList(navController, charModel) }
        composable("detail") { CharacterDetail(navController, charModel) }
        /*...*/
    }
}

@Composable
fun Attributes(viewModel: ComicCharacterViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(
                width = 3.dp,
                color = Purple200,
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                color = Color.Transparent
            )
            .padding(10.dp)
    ) {
        Text(text = viewModel.attributeText.value ?: "no attributes")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleMarvelTheme {
        val navController = rememberNavController()
        MarvelCharacterList(navController, ComicCharacterViewModel(MarvelApplication.repository))
    }
}
