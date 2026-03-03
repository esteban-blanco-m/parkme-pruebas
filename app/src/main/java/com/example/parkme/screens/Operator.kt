package com.example.parkme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkme.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CreateParkingVisual(modifier: Modifier = Modifier) {
    val pill = RoundedCornerShape(50)
    val card = RoundedCornerShape(24)
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.back))
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoparkme),
                contentDescription = "Logo de la app",
                modifier = Modifier
                    .width(160.dp)
                    .height(95.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .width(2.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Crear\nparqueadero",
                color = colorResource(R.color.black),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
                textAlign = TextAlign.Start
            )
        }


        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("$ Precio por hora", fontSize = 12.sp) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.grisClaro),
                    unfocusedBorderColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("$ Precio por minuto", fontSize = 12.sp) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.grisClaro),
                    unfocusedBorderColor = Color.Gray
                )
            )
        }

        Spacer(Modifier.height(12.dp))

        LabelAndRight(
            label = "Disponible",
            right = { CircleCheck(checked = true) }
        )

        LabelAndRight(
            label = "Cargador EV",
            right = { CircleCheck(checked = true) }
        )

        LabelAndRight(
            label = "Tarifa plena",
            right = {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("$ Tarifa", fontSize = 12.sp) },
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(140.dp)
                        .height(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(R.color.grisClaro),
                        unfocusedBorderColor = Color.Gray
                    )
                )
            }
        )

        LabelAndRight(
            label = "Reglas del parqueadero",
            right = {
                Button(
                    onClick = {},
                    shape = pill,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text("Ingresar", color = Color.Blue, fontWeight = FontWeight.Bold)
                }
            }
        )

        Spacer(Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(colorResource(R.color.grisClaro), card)
                .padding(14.dp)
        ) {
            Text(
                text = "Fotos del parqueadero",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
                    .border(2.dp, Color.Black, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add", modifier = Modifier.size(44.dp))
            }
        }


        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {},
            shape = pill,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Agregar Ubicación", color = Color.DarkGray, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        LabelAndRight(
            label = "Hora de apertura",
            right = { TimePill("00:00") }
        )

        LabelAndRight(
            label = "Hora de cierre",
            right = { TimePill("23:59") }
        )

        Spacer(Modifier.height(12.dp))

        val dayLabels = listOf("L", "M", "M", "J", "V", "S", "D")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dayLabels.forEach { d ->
                DayLetter(d)
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CircleCheck(checked = true)
            CircleCheck(checked = false)
            CircleCheck(checked = true)
            CircleCheck(checked = false)
            CircleCheck(checked = true)
            CircleCheck(checked = false)
            CircleCheck(checked = true)
        }

        Spacer(Modifier.height(18.dp))

        Button(
            onClick = {},
            shape = pill,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(54.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue))
        ) {
            Text("Crear", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        Spacer(Modifier.height(12.dp))
    }
}

@Composable
fun EditParkingVisual(modifier: Modifier = Modifier) {
    val pill = RoundedCornerShape(50)
    val card = RoundedCornerShape(24)
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.back))
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoparkme),
                contentDescription = "Logo de la app",
                modifier = Modifier
                    .width(160.dp)
                    .height(95.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .width(2.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Editar\nparqueadero",
                color = colorResource(R.color.black),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
                textAlign = TextAlign.Start
            )
        }


        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("$ Precio por hora", fontSize = 12.sp) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.grisClaro),
                    unfocusedBorderColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("$ Precio por minuto", fontSize = 12.sp) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.grisClaro),
                    unfocusedBorderColor = Color.Gray
                )
            )
        }

        Spacer(Modifier.height(12.dp))

        LabelAndRight(
            label = "Disponible",
            right = { CircleCheck(checked = true) }
        )

        LabelAndRight(
            label = "Cargador EV",
            right = { CircleCheck(checked = true) }
        )

        LabelAndRight(
            label = "Tarifa plena",
            right = {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("$ Tarifa", fontSize = 12.sp) },
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(140.dp)
                        .height(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(R.color.grisClaro),
                        unfocusedBorderColor = Color.Gray
                    )
                )
            }
        )

        LabelAndRight(
            label = "Reglas del parqueadero",
            right = {
                Button(
                    onClick = {},
                    shape = pill,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text("Ingresar", color = Color.Blue, fontWeight = FontWeight.Bold)
                }
            }
        )

        Spacer(Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(colorResource(R.color.grisClaro), card)
                .padding(14.dp)
        ) {
            Text(
                text = "Fotos del parqueadero",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
                    .border(2.dp, Color.Black, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add", modifier = Modifier.size(44.dp))
            }
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {},
            shape = pill,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Agregar Ubicación", color = Color.DarkGray, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        LabelAndRight(
            label = "Hora de apertura",
            right = { TimePill("00:00") }
        )

        LabelAndRight(
            label = "Hora de cierre",
            right = { TimePill("23:59") }
        )

        Spacer(Modifier.height(12.dp))

        val dayLabels = listOf("L", "M", "M", "J", "V", "S", "D")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dayLabels.forEach { d ->
                DayLetter(d)
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CircleCheck(checked = true)
            CircleCheck(checked = false)
            CircleCheck(checked = true)
            CircleCheck(checked = false)
            CircleCheck(checked = true)
            CircleCheck(checked = false)
            CircleCheck(checked = true)
        }

        Spacer(Modifier.height(18.dp))

        Button(
            onClick = {},
            shape = pill,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.6f)
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue)),
        ) {
            Text("Crear", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun LabelAndRight(
    label: String,
    right: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 16.sp)
        right()
    }
}

@Composable
private fun CircleCheck(checked: Boolean) {
    Surface(
        modifier = Modifier.size(28.dp),
        shape = CircleShape,
        color = Color.Transparent,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        if (checked) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Check",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun TimePill(time: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color(0xFFE0E0E0),
        border = BorderStroke(0.dp, Color.Transparent)
    ) {
        Text(
            text = time,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
            color = Color(0xFF1565C0),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DayLetter(letter: String) {
    Surface(
        modifier = Modifier.size(34.dp),
        shape = CircleShape,
        color = Color(0xFFE0E0E0)
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(letter, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateParkingVisualPreview() {
    CreateParkingVisual(modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun EditParkingVisualPreview() {

    EditParkingVisual(modifier = Modifier.fillMaxSize())
}