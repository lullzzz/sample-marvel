package com.lullzzzz.samplemarvel.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lullzzzz.samplemarvel.Attributes
import com.lullzzzz.samplemarvel.ui.theme.Purple200
import com.lullzzzz.samplemarvel.viewmodel.ComicCharacterViewModel
import kotlinx.coroutines.launch

@Composable
fun MarvelCharacterList(
    navController: NavController,
    viewModel: ComicCharacterViewModel
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        val characterList = viewModel.characterList.observeAsState()
        // viewModel.detail.value = null
        Attributes(viewModel = viewModel)
        characterList.value?.results?.let {
            for (comicCharacter in it) {
                val expanded = mutableStateOf(false)
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
                        .clickable {
                            expanded.value = !expanded.value
                        }
                ) {
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            val url = comicCharacter.thumbnail?.path
                            val extension = comicCharacter.thumbnail?.extension
                            url?.let {
                                NetworkImageComponentGlide(
                                    url = "$url/standard_medium.$extension",
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(3.dp)
                                        .weight(1F, false)
                                )
                            }

                            Text(
                                text = comicCharacter.name ?: "Unknown name",
                                modifier = Modifier
                                    .height(IntrinsicSize.Min)
                                    .weight(1f, false)
                                    .align(Alignment.CenterVertically)
                                    .fillMaxHeight(),
                                color = Color.Black,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                            Button(
                                modifier = Modifier
                                    .size(100.dp, 50.dp)
                                    .padding(3.dp)
                                    .background(color = Color.Green)
                                    .align(Alignment.CenterVertically)
                                    .weight(1F, false),
                                onClick = {
                                    viewModel.detail.value = comicCharacter
                                    navController.navigate("detail")
                                }
                            ) {
                                Text(
                                    text = "Open details",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        if (expanded.value) {
                            Row() {
                                Text(text = comicCharacter.description ?: "No short bio")
                            }
                        }
                    }


                }
            }
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if (viewModel.prevAvailable.value == true) {
                Button(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(color = Color.Green),
                    onClick = {
                        viewModel.fetchPrevData()
                        scope.launch {
                            scrollState.scrollTo(0)
                        }
                    }
                ) {
                    Text(text = "Prev")
                }
            }
            if (viewModel.nextAvailable.value == true) {
                Button(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(color = Color.Green),
                    onClick = {
                        viewModel.fetchNextData()
                        scope.launch {
                            scrollState.scrollTo(0)
                        }
                    }
                ) {
                    Text(text = "Next")
                }
            }
        }

    }
}

@Composable
fun NetworkImageComponentGlide(
    url: String, modifier: Modifier = Modifier
) {
    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }
    val sizeModifier = modifier
        .sizeIn(maxHeight = 200.dp)

    val context = LocalContext.current
    DisposableEffect(url) {
        val glide = Glide.with(context)
        val target = object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                image = null
                drawable = placeholder
            }

            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                image = bitmap.asImageBitmap()
            }
        }
        glide
            .asBitmap()
            .load(url)
            .into(target)

        onDispose {
            image = null
            drawable = null
            glide.clear(target)
        }
    }

    val theImage = image
    val theDrawable = drawable
    if (theImage != null) {
        Column(
            modifier = sizeModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(bitmap = theImage, contentDescription = null)
        }
    } else if (theDrawable != null) {
        Canvas(modifier = sizeModifier) {
            drawIntoCanvas { canvas ->
                theDrawable.draw(canvas.nativeCanvas)
            }
        }
    }
}