package com.example.parkme.screens

import androidx.compose.material3.Button
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkme.navigation.AppScreens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.parkme.R

@Composable
fun SearchMap(navController: NavController) {
    Box(modifier = Modifier
        .background(colorResource(R.color.back))
        .fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.mapa),
            contentDescription = "Mapa estático de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Button(
            onClick = { navController.navigate(AppScreens.SearchFilters.name) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 38.dp)
                .size(50.dp),
            shape = RoundedCornerShape(28),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menú",
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 0.dp, end = 0.dp, bottom = 3.dp)
                .background(
                    color = colorResource(R.color.grisClaro),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(24.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = buildAnnotatedString {
                    append("Buscando tu ")
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.blue),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("estacionamiento")
                    }
                }, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
            )

            Text(
                text = "Encontrando estacionamientos cerca de ti...",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = { navController.navigate(AppScreens.HomeUser.name) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Cancelar búsqueda",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
@Preview
fun Searchpreview() {
    val navController = rememberNavController()
    SearchMap(navController = navController)
}