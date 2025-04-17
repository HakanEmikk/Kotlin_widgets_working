package com.example.widgets

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.widgets.ui.theme.WidgetsTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WidgetsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RadioButtonPage(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    WidgetsTheme {
        RadioButtonPage()
    }
}
@Composable
fun RadioButtonPage(modifier: Modifier = Modifier) {
    val radioButtonSelected= remember { mutableStateOf(0) }
    val teamList= listOf("Real Madrid","Barcelona")
    Column( modifier=Modifier.fillMaxSize(),

        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Column {
        teamList.forEachIndexed{index,team-> Row(modifier=Modifier.clickable {
            radioButtonSelected.value=index
            Log.e("RadioButton",team)
        },
            verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (team== teamList[radioButtonSelected.value]),
                    onClick = {
                        radioButtonSelected.value=index
                        Log.e("RadioButton",team)
                    }
                )
            Text(team)
            }}
        }
    }

}

@Composable
fun GestureDetectorPage( modifier: Modifier = Modifier) {
    Column( modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
     Box(modifier = Modifier
         .size(100.dp)
         .background(Color.Red)
         .pointerInput(Unit) {
             detectTapGestures(
                 onTap = { Log.e("Box", "Tıklandı") }
             )

         }
     )
    }
}



@Composable
fun CheckboxPage( modifier: Modifier = Modifier) {
val checkBoxState= remember { mutableStateOf(false) }
    Column( modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        )
    {
        Row (verticalAlignment = Alignment.CenterVertically){
            Checkbox(
                checked = checkBoxState.value,
                onCheckedChange = {
                    checkBoxState.value = it
                },
                colors = CheckboxDefaults.colors(
                checkedColor = Color.Red
                )
            )
            Text(text = "Jetpack Compose", modifier = Modifier.padding(start = 10.dp))
        }
    }
}

@Composable
fun SwitchPage( modifier: Modifier = Modifier) {
    val switchstate= remember { mutableStateOf((false)) }
Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally
) {
Switch(
    checked = switchstate.value,
    onCheckedChange = {
        switchstate.value=it
        Log.e("switch",it.toString())
    }, colors = SwitchDefaults.colors(
        checkedTrackColor = Color.Red,
        checkedThumbColor = Color.Blue,
        uncheckedTrackColor =Color.Red,
        uncheckedThumbColor = Color.Blue
    )
)
    Button(
        onClick = {
            Log.e("switch son durum",switchstate.value.toString())
        }
    ) { Text(text = "Göster") }
    

}

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FabPage( modifier: Modifier = Modifier) {
    Scaffold(
        content = {

        },
        floatingActionButton = {
ExtendedFloatingActionButton(
    onClick = {
        Log.e("fab","tıklandı")
    },

    containerColor = Color.Red,
    content = {

        Icon(
            painter = painterResource(id = R.drawable.add_image),
            contentDescription = "", tint =Color.White )

            Text("Ekle", color = Color.White)


    }

)
        }
    )
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
        label = { Text(text = "Veri Giriniz") },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Green,
            focusedTextColor = Color.Red,
            disabledTextColor = Color.Cyan

        ),
visualTransformation = PasswordVisualTransformation(),
        )
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

