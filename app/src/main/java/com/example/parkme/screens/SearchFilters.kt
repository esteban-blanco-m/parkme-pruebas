package com.example.parkme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.parkme.R
import com.example.parkme.navigation.AppScreens

@Composable
fun SearchFilters() {
    var isEvChargerEnabled: Boolean = remember { true }
    var distanceSliderValue: Float = remember { 0.5f }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.mapa),
            contentDescription = "Fondo del mapa",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.grisClaro),
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 24.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú",
                        tint = Color.Black
                    )
                }

                Text(
                    text = "Filtrar por",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Text("")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Precio",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text("")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(
                                    colorResource(R.color.gris),
                                    RoundedCornerShape(50)
                                )
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                        ) {
                            Text("Min", color = Color.DarkGray, fontSize = 14.sp)
                        }
                        Text(text = "   —   ", color = Color.Gray)
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFD4D4D4), RoundedCornerShape(50))
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                        ) {
                            Text("Max", color = Color.DarkGray, fontSize = 14.sp)
                        }
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Cargador EV",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Switch(
                        checked = isEvChargerEnabled,
                        onCheckedChange = { isEvChargerEnabled = it },
                        colors = SwitchDefaults.colors(checkedTrackColor = colorResource(R.color.blue))
                    )
                }
            }

            Text("")
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Distancia",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Slider(
                    value = distanceSliderValue,
                    onValueChange = { distanceSliderValue = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.DarkGray,
                        activeTrackColor = Color.DarkGray,
                        inactiveTrackColor = Color.LightGray
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "0 mts", fontSize = 12.sp, color = Color.DarkGray)
                    Text(text = "500 mts", fontSize = 12.sp, color = Color.DarkGray)
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF2F2F2),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Buscando tu ")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF007BFF),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("estacionamiento")
                    }
                },
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = "Encontrando estacionamientos cerca de ti...",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
@Preview
fun Filterspreview() {
    SearchFilters()
}