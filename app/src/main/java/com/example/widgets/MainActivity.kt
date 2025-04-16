package com.example.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.widgets.ui.theme.WidgetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WidgetsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WidgetsPage(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WidgetsPage( modifier: Modifier = Modifier) {
    val textFieldOutput= remember { mutableStateOf("") }
    val textFieldInput= remember { mutableStateOf("") }

Column(modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally,)
{
    Text(text ="Gelen Veri : ${textFieldInput.value}" )
    TextField(
        value = textFieldInput.value,
        onValueChange = {textFieldInput.value=it},
        label = { Text(text = "Veri Giriniz") })
    Button(onClick = {textFieldInput.value=textFieldInput.value}) {
        Text(text = "Veriyi al")
    }
}
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    WidgetsTheme {
        WidgetsPage()
    }
}