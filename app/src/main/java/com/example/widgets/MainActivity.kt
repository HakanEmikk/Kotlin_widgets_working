package com.example.widgets

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.widgets.ui.theme.WidgetsTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WidgetsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DynamicDropdownMenuPage(

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
        DynamicDropdownMenuPage()
    }
}

@Composable
fun DynamicDropdownMenuPage(modifier: Modifier = Modifier) {
   val countyList = listOf("türkiye", "İtalya", "Almanya", "Japonya", "Rusya", "Çin")

    val menuStartControl = remember { mutableStateOf(false) }
    val selectIndex= remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
           Row(verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center,
               modifier = Modifier.size(100.dp,50.dp).
               clickable(onClick = {
                   menuStartControl.value=true
               })


               ) {
               Text(text = countyList[selectIndex.value])
               Image(painter = painterResource(id = R.drawable.dropdown_menu_image), contentDescription = "")
           }
            DropdownMenu(
                expanded = menuStartControl.value,
                onDismissRequest = {
                    menuStartControl.value = false
                }
            ) {
                countyList.forEachIndexed{index,country ->
                    DropdownMenuItem(
                        onClick = {
                            Log.e("Menu", "Ülke  Seçildi:$country")
                            menuStartControl.value = false
                            selectIndex.value=index
                        },
                        text = {
                            Text(country)
                        },
                    )
                }
            }
        }
        Button(
            onClick = {

            }
        ) {
            Text(text = "Göster")
        }
    }
}

@Composable
fun DropdownMenuPage(modifier: Modifier = Modifier) {
    val menuStartControl= remember { mutableStateOf(false) }
    Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Box{
            Button(
                onClick = {
                    menuStartControl.value=true
                }
            ) {
                Text(text = "Göster")
            }
            DropdownMenu(
                expanded = menuStartControl.value,
                onDismissRequest = {
                    menuStartControl.value=false
                }
            ) {
                DropdownMenuItem(
                    onClick = {
                        Log.e("Menu", "Sil  Seçildi")
                        menuStartControl.value = false
                    },
                    text = {
                        Text("sil")
                    },
                )
                DropdownMenuItem(
                    onClick = {
                        Log.e("Menu", "Güncelle  Seçildi")
                        menuStartControl.value = false
                    },
                    text = {
                        Text("Güncelle")
                    },
                )
            }
    }
}
}

@Composable
fun ImagePage(modifier: Modifier = Modifier) {
    val activity=(LocalContext.current as Activity)
Column {
    Image(contentDescription = "",
        bitmap = ImageBitmap.
        imageResource(id =
            activity.resources.getIdentifier("add_image",
                "drawable",
                activity.packageName
            )
        )

    )
}
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebviewPage(modifier: Modifier = Modifier) {
val url=""
AndroidView(factory = {
    WebView(it).apply {
        layoutParams=ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        webViewClient= WebViewClient()
        loadUrl(url)
    }
},
    update =
        {
it.loadUrl(url)
        }
)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressIndicatorPage(modifier: Modifier = Modifier) {
    val progressState= remember { mutableStateOf(false) }
    val sliderValue= remember { mutableStateOf(0f) }
    Column( modifier=Modifier.fillMaxSize(),

        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
if (progressState.value){
    CircularProgressIndicator(color = Color.Red)
}
Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
    Button(
        onClick = {
            progressState.value=true
        }
    ) {
        Text(text = "Başla")
    }
    Button(
        onClick = {
            progressState.value=false
        }
    ) {
        Text(text = "Dur")
    }
}

        Text(text = "Sonuç : ${sliderValue.value.toInt()}")

        Slider(
            value = sliderValue.value,
            onValueChange = {
                sliderValue.value=it
            },
            valueRange = 0f..100f,
            modifier = Modifier.padding(all = 20.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Blue,
                inactiveTickColor = Color.Black
            )
        )

Button(
    onClick = {
        Log.e("Slider",sliderValue.value.toString())
    }
) {
    Text(text = "Göster")
}
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

