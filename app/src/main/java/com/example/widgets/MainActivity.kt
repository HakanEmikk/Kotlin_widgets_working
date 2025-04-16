package com.example.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val textFieldInputOutlined= remember { mutableStateOf("") }
Column(modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally,)
{
    Text(text ="Gelen Veri : ${textFieldOutput.value}" ,
    color = Color.White,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
style = TextStyle(background = Color.Black,)
        )
    TextField(
        value = textFieldInput.value,
        onValueChange = {textFieldInput.value=it},
        label = { Text(text = "Veri Giriniz") })
    Button(onClick = {textFieldOutput.value=textFieldInput.value},
        colors =ButtonDefaults.buttonColors(
            containerColor = Color.Red, contentColor = Color.White
        ), border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(text = "Veriyi al")
    }
    OutlinedTextField(
        value = textFieldInputOutlined.value,
        onValueChange = {textFieldInputOutlined.value=it},
        label = { Text(text = "Veri Giriniz") })
    OutlinedButton(onClick = {textFieldOutput.value=textFieldInputOutlined.value}, colors =ButtonDefaults.buttonColors(
        containerColor = Color.Red, contentColor = Color.White
    ), border = BorderStroke(1.dp, Color.Black)) {
        Text(text = "Veriyi al Outlined")
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