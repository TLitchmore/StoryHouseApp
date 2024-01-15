package com.example.storyhouse

import android.R
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.storyhouse.ui.theme.StoryHouseTheme
import java.io.File


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoryHouseTheme {
                    StoryHouseApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

@Composable
fun StoryHouseApp(modifier: Modifier = Modifier){
    var shouldShowStart by rememberSaveable { mutableStateOf(true) }
    Surface(modifier) {
       if(shouldShowStart){
           LandingPage(onContinueClicked = { shouldShowStart = false})
       } else {
           ARScreen(modifier = Modifier.fillMaxSize())
       }
    }
}

@Composable
fun LandingPage(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
   Surface (color = MaterialTheme.colorScheme.primary) {
       Column (modifier = modifier
           .fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ){
           Text(
               text = stringResource(com.example.storyhouse.R.string.title_of_app),
               style = MaterialTheme.typography.headlineMedium.copy(
                   fontWeight = FontWeight.ExtraBold
               ),
               modifier = Modifier.padding(bottom = 400.dp)
           )
           ElevatedButton(
               onClick = onContinueClicked) {
               Text(stringResource(com.example.storyhouse.R.string.start_button_text))

           }
       }
   }
}

@Composable
private fun ARScreen(
    modifier: Modifier = Modifier,
){
    Surface(
        color = Color.Black
    ) {
        Text(text = "hi", color = Color.White)
    }
     AndroidView(factory = { context -> PlayerView(context).apply {
        val player = ExoPlayer.Builder(context).build()
        this.player=player
         val uri = Uri.parse("android.resource://" + "com.example.storyhouse"+ "/"+ com.example.storyhouse.R.raw.sea)
         val video = MediaItem.fromUri(uri)
         player.setMediaItem(video)
         player.play()
    }})
}


@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "ARScreenPreviewDark"
)
@Preview(showBackground = true)
@Composable
fun ARScreenPreview(){
    StoryHouseTheme {
        ARScreen()
    }
}
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "StoryHouseAppPreviewDark"
)

@Preview (showBackground = true)
@Composable
fun StoryHouseAppPreview(){
    StoryHouseTheme {
        StoryHouseApp(Modifier.fillMaxSize())
    }
}
