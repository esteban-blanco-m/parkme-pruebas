package com.example.parkme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.parkme.R
import com.example.parkme.navigation.AppScreens

@Composable
fun LogIn(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf("Usuario") }

    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.back))
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoparkme),
            contentDescription = "Logo de la app",
            modifier = Modifier
                .height(310.dp)
                .padding(top = 10.dp, bottom = 5.dp)
                .width(400.dp),
            contentScale = ContentScale.Fit
        )
        Row {
            Text("Encuentra tu", color = colorResource(R.color.black), fontWeight = FontWeight.Bold)
            Text(" parqueadero", color = colorResource(R.color.blue), fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // todo esto es temp para efectos graficos xd
        Text(
            "Selecciona tu perfil (Temporal):",
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            RadioButton(
                selected = selectedRole == "Usuario",
                onClick = { selectedRole = "Usuario" }
            )
            Text("Usuario")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = selectedRole == "Operador",
                onClick = { selectedRole = "Operador" }
            )
            Text("Operador")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Email o número de teléfono",
            color = colorResource(R.color.black),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp, bottom = 8.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = {
                Text(
                    "tu@email.com o 3XX-XXX XXXX",
                    color = colorResource(R.color.black)
                )
            },
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Contraseña",
            color = colorResource(R.color.black),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp, bottom = 8.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    ".........",
                    color = colorResource(R.color.black),
                    fontWeight = FontWeight.ExtraBold
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Text(
                    text = if (passwordVisible) "Ocultar" else "Mostrar",
                    color = colorResource(R.color.black),
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { passwordVisible = !passwordVisible }
                )
            },
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (selectedRole == "Usuario")
                    navController.navigate(AppScreens.HomeUser.name)
                else
                    navController.navigate(AppScreens.HomeOperator.name)
            },
            modifier = Modifier.fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue),
                contentColor = colorResource(R.color.white)
            )
        ) {
            Text("Iniciar Sesión", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("¿No tienes cuenta?")
        Button(
            onClick = {
                navController.navigate(AppScreens.SignUp.name)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.transparent),
                contentColor = colorResource(R.color.blue)
            )
        ) {
            Text("Regístrate")
        }
    }
}

@Composable
@Preview
fun LogInpreview() {
    val navController = rememberNavController()
    LogIn(navController = navController)
}