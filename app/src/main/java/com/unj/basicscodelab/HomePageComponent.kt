package com.unj.basicscodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.exp

object HomePageComponent {
    //var modifier: Modifier = Modifier

    @Composable
    fun ColumnSetup(text:String, modifier:Modifier = Modifier){
        Column(modifier = modifier.padding(16.dp)) {
            for (i in 0..10) {
                Greeting(name = "$text $i")
            }
        }
    }

    @Composable
    fun Greetings(
        modifier: Modifier = Modifier,
        names: List<String> = listOf("World", "Compose")
    ) {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items (items = names) { name ->
                Greeting(name)
            }
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        val expanded = remember{mutableStateOf(false)}
        val extraPadding: Dp = if(expanded.value) 48.dp else 0.dp
            Row(modifier=modifier){
                Column(modifier = modifier.padding(8.dp).padding(bottom = extraPadding)){
                    Text(
                        text = "Hello",

                    )
                    Text(
                        text = "$name!"
                    )
                }

                ElevatedButton(onClick = {
                    expanded.value = !expanded.value
                } ) {
                    if(expanded.value)
                        Text("Show More")
                    else
                        Text("Show Less")
                }
            }

    }
}