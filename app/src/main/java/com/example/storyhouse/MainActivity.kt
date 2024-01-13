package com.example.storyhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.storyhouse.ui.theme.StoryHouseTheme

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
fun StoryHouseApp(modifier: Modifier=Modifier){
    Surface (
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ){
        LandingPage()
    }
}

@Composable
fun LandingPage(modifier: Modifier = Modifier) {
    var shouldShowHouse by remember { mutableStateOf(true) }

   Surface (color = MaterialTheme.colorScheme.primary) {
       Column (modifier = modifier.padding(48.dp).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
       ){
           Text(text = "StoryHouse")
           ElevatedButton(onClick = {shouldShowHouse = false}) {
               Text("Start")
           }
       }
   }
}

@Preview(showBackground = true)
@Composable
fun LandingPagePreview() {
    StoryHouseTheme {
        StoryHouseApp()
    }
}