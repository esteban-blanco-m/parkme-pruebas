package com.example.parkme.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.delay

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

    val parkingLots = remember {
        listOf(
            ParkingLot("1", "Parqueadero Javeriana", LatLng(4.627293, -74.063228), "$4.000", 12),
            ParkingLot("2", "Parqueadero Teusaquillo", LatLng(4.632000, -74.068000), "$3.500", 5),
            ParkingLot("3", "ParkMe Central", LatLng(4.625000, -74.060000), "$5.000", 2)
        )
    }

    val defaultLocation = LatLng(4.626072, -74.071427)

    // states de bsqueda y parqueadero
    var selectedParkingLot by remember { mutableStateOf<ParkingLot?>(null) }
    var isSearching by remember { mutableStateOf(true) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 15f)
    }

    val carBitmap = remember { resizeMapIcon(context, R.drawable.blackcar, 35, 70) }
    val pinBitmap = remember { resizeMapIcon(context, R.drawable.pinmaplogo, 45, 45) }

    val infiniteTransition = rememberInfiniteTransition(label = "buscando")
    val alphaAnim by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "BuscandoAlpha"
    )

    // Cálculo del parqueadero más cercano con un retraso para simular la búsqueda
    LaunchedEffect(defaultLocation) {
        isSearching = true
        delay(2500) // Simula 2.5 segundos de búsqueda conectando al servidor

        var minDistance = Float.MAX_VALUE
        var closest: ParkingLot? = null

        // Calcular la distancia usando la librería nativa de Location
        for (parking in parkingLots) {
            val results = FloatArray(1)
            Location.distanceBetween(
                defaultLocation.latitude, defaultLocation.longitude,
                parking.location.latitude, parking.location.longitude,
                results
            )
            if (results[0] < minDistance) {
                minDistance = results[0]
                closest = parking
            }
        }

        selectedParkingLot = closest
        isSearching = false
    }

    Box(modifier = Modifier
        .background(colorResource(R.color.back))
        .fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            // desactivar la bola pa reemplazar con el carro
            properties = MapProperties(isMyLocationEnabled = false),
            uiSettings = MapUiSettings(myLocationButtonEnabled = false)
        ) {
            Marker(
                state = MarkerState(position = defaultLocation),
                title = "Mi Ubicación",
                icon = BitmapDescriptorFactory.fromBitmap(carBitmap)
            )

            parkingLots.forEach { parking ->
                Marker(
                    state = MarkerState(position = parking.location),
                    title = parking.name,
                    snippet = "Cupos: ${parking.availableSpots} - Precio: ${parking.pricePerHour}",
                    icon = BitmapDescriptorFactory.fromBitmap(pinBitmap),
                    onClick = {
                        selectedParkingLot = parking
                        false
                    }
                )
            }

            // linea para parqueadero mas cercano
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
            if (isSearching) {
                // buscando
                Text(
                    text = buildAnnotatedString {
                        append("Buscando el ")
                        withStyle(style = SpanStyle(color = colorResource(R.color.blue), fontWeight = FontWeight.Bold)) {
                            append("parqueadero más cercano")
                        }
                        append("...")
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.alpha(alphaAnim) // efecto de parpadeo suave
                )

                // Barra de progreso lineal para dar la sensación de carga
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    color = colorResource(R.color.blue)
                )

            } else if (selectedParkingLot != null) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Ver Detalles", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                onClick = { navController.navigate(AppScreens.HomeUser.name) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (isSearching) 24.dp else 12.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Cancelar búsqueda", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Devuelve un Bitmap nativo de Android en lugar de BitmapDescriptor
fun resizeMapIcon(context: android.content.Context, resId: Int, widthDp: Int, heightDp: Int): Bitmap {
    val density = context.resources.displayMetrics.density
    val widthPx = (widthDp * density).toInt()
    val heightPx = (heightDp * density).toInt()

    val imageBitmap = BitmapFactory.decodeResource(context.resources, resId)
    // Se devuelve el Bitmap redimensionado de forma cruda, sin tocar Google Maps aún
    return Bitmap.createScaledBitmap(imageBitmap, widthPx, heightPx, false)
}