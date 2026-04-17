package com.example.parkme.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.parkme.R
import com.example.parkme.models.ParkingLot
import com.example.parkme.navigation.AppScreens
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun SearchMap(navController: NavController) {
    val context = LocalContext.current

    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> hasLocationPermission = isGranted }
    )

    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

  //aca toca cambiar esto con lo de firebase, cuando este ya toodo good pq los paqrquederos deben estar guardados en un directorio del json
    val parkingLots = remember {
        listOf(
            ParkingLot("1", "Parqueadero Javeriana", LatLng(4.627293, -74.063228), "$4.000", 12),
            ParkingLot("2", "Parqueadero Teusaquillo", LatLng(4.632000, -74.068000), "$3.500", 5),
            ParkingLot("3", "ParkMe Central", LatLng(4.625000, -74.060000), "$5.000", 2)
        )
    }

    // estado de la seleccion y ubicacion
    var selectedParkingLot by remember { mutableStateOf<ParkingLot?>(null) }
    val defaultLocation = LatLng(4.627293, -74.063228)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 14f)
    }

    Box(modifier = Modifier
        .background(colorResource(R.color.back))
        .fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            // loc en tiempo real
            properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
            uiSettings = MapUiSettings(myLocationButtonEnabled = true)
        ) {
            //iterar sobre la lista dinámica para crear los marcadores
            parkingLots.forEach { parking ->
                Marker(
                    state = MarkerState(position = parking.location),
                    title = parking.name,
                    snippet = "Cupos: ${parking.availableSpots} - Precio: ${parking.pricePerHour}",
                    onClick = {
                        selectedParkingLot = parking
                    }
                )
            }

            selectedParkingLot?.let { destination ->
                Polyline(
                    points = listOf(defaultLocation, destination.location),
                    color = Color(0xFF0056D2),
                    width = 12f
                )
            }
        }

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
                .background(
                    color = colorResource(R.color.grisClaro),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            if (selectedParkingLot == null) {
                // state: searching
                Text(
                    text = buildAnnotatedString {
                        append("Buscando tu ")
                        withStyle(style = SpanStyle(color = colorResource(R.color.blue), fontWeight = FontWeight.Bold)) {
                            append("estacionamiento")
                        }
                    }, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
                )
                Text(
                    text = "Selecciona un parqueadero en el mapa...",
                    fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(top = 8.dp)
                )
            } else {
                // state: parkme located
                Text(
                    text = selectedParkingLot!!.name,
                    fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black
                )
                Text(
                    text = "Cupos disponibles: ${selectedParkingLot!!.availableSpots} | Tarifa: ${selectedParkingLot!!.pricePerHour}",
                    fontSize = 16.sp, color = Color.DarkGray, modifier = Modifier.padding(top = 8.dp)
                )
                Button(

                    onClick = { navController.navigate(AppScreens.ParkingLotDetail.name) },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue)),
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Ver Detalles del Parqueadero", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                onClick = { navController.navigate(AppScreens.HomeUser.name) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Cancelar búsqueda", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}