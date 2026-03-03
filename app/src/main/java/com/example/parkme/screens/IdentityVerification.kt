package com.example.parkme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.parkme.R
import com.example.parkme.navigation.AppScreens

@Composable
fun IdentityVerification(navController: NavController, selectedRole: String?) {
    val scrollState = rememberScrollState()
    var selectedDocument by remember { mutableStateOf("Cedula") }

    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.back))
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoparkme),
            contentDescription = "Logo de la app",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(top = 10.dp, bottom = 5.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Verificación de\nIdentidad",
            color = colorResource(R.color.black),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 36.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ten listo un documento de identidad para que podamos tomarle una foto y así verificar tu identidad.",
            color = colorResource(R.color.black),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tu información se mantendrá privada.",
            color = colorResource(R.color.black),
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { selectedDocument = "Cedula" }
                .padding(vertical = 8.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                RadioButton(
                    selected = selectedDocument == "Cedula",
                    onClick = { selectedDocument = "Cedula" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorResource(R.color.blue),
                        unselectedColor = Color.Transparent
                    )
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, RoundedCornerShape(50))
                    .padding(vertical = 16.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = "Cédula de Ciudadanía",
                    color = colorResource(R.color.black),
                    fontSize = 16.sp
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { selectedDocument = "Pasaporte" }
                .padding(vertical = 8.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                RadioButton(
                    selected = selectedDocument == "Pasaporte",
                    onClick = { selectedDocument = "Pasaporte" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorResource(R.color.blue),
                        unselectedColor = Color.Transparent
                    )
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, RoundedCornerShape(50))
                    .padding(vertical = 16.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = "Pasaporte",
                    color = colorResource(R.color.black),
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    if (selectedRole == "Usuario") {
                        navController.navigate(AppScreens.HomeUser.name)
                    } else {
                        navController.navigate(AppScreens.HomeOperator.name)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(52.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue),
                    contentColor = colorResource(R.color.white)
                )
            ) {
                Text(
                    text = "Completar Registro",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview
fun IdentityPreview() {
    val navController = rememberNavController()
    IdentityVerification(navController = navController, selectedRole = "Usuario")
}