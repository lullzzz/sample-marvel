package com.lullzzzz.samplemarvel.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lullzzzz.samplemarvel.Attributes
import com.lullzzzz.samplemarvel.ui.theme.Purple200
import com.lullzzzz.samplemarvel.ui.theme.Purple700
import com.lullzzzz.samplemarvel.viewmodel.ComicCharacterViewModel

@Composable
fun CharacterDetail(navController: NavController, charModel: ComicCharacterViewModel) {
    val character = charModel.detail
    Column() {
        Attributes(viewModel = charModel)
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
            Column() {
                Text(
                    color = Purple700,
                    fontStyle = FontStyle.Italic,
                    text = character.value?.name ?: "no name"
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = character.value?.description ?: "no bio"
                )
                val urls = character.value?.urls
                urls?.let {
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = "Detail links:"
                    )
                    for (url in it) {
                        val type = when (url.type) {
                            "comiclink" -> "Comics"
                            "wiki" -> "Wiki"
                            "detail" -> "Details"
                            else -> null
                        }
                        val context = LocalContext.current
                        Button(
                            modifier = Modifier
                                .padding(0.dp, 5.dp)
                                .background(Purple200, RoundedCornerShape(3.dp)),
                            onClick = {
                                url.url?.let {
                                    openLink(context, url.url)
                                }
                            }) {
                            Text(text = type ?: "")
                        }
                        // Text(text = url.url ?: "")
                    }
                }
            }
        }
    }
}

fun openLink(context: Context, url: String) {
    val linkIntent = Intent()
    linkIntent.action = Intent.ACTION_VIEW
    linkIntent.data = Uri.parse(url)
    context.startActivity(linkIntent)
}
