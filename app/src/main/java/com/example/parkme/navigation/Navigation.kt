package com.example.parkme.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parkme.screens.*
import com.example.parkme.viewmodel.AppViewModel

enum class AppScreens {
    HomeUser,
    LogIn,
    SignUp,
    IdentityVerification,
    SearchMap,
    ParkingLotDetail,
    RateParkingLot,
    MyActivity,
    Operator,
    UserProfile,
    OperatorProfile,
    HomeOperator,
    MyActivityOperator,
    CreateParking,
    EditParking,
    ChatOp,
    ChatCli
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: AppViewModel = viewModel()
    val authState by viewModel.authState.collectAsState()

    if (authState.isCheckingSession) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val startRoute = when {
        authState.isAuthenticated && authState.isVerified -> {
            if (authState.userRole == "Operador") AppScreens.HomeOperator.name else AppScreens.HomeUser.name
        }
        authState.isAuthenticated && !authState.isVerified -> {
            AppScreens.IdentityVerification.name
        }
        else -> AppScreens.LogIn.name
    }

    NavHost(
        navController = navController,
        startDestination = startRoute,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) }
    ) {

        composable(AppScreens.LogIn.name) { LogIn(navController, viewModel) }
        composable(AppScreens.SignUp.name) { SignUp(navController, viewModel) }
        composable(AppScreens.IdentityVerification.name) { IdentityVerification(navController, viewModel) }
        composable(AppScreens.HomeUser.name) { HomeUser(navController) }
        composable(AppScreens.HomeOperator.name) { HomeOperator(navController) }
        composable(AppScreens.SearchMap.name) { SearchMap(navController) }
        composable(AppScreens.RateParkingLot.name) { RateParkingLot(navController) }
        composable(AppScreens.MyActivity.name) { MyActivity(navController) }
        composable(AppScreens.UserProfile.name) { ProfileScreen(navController, viewModel) }
        composable(AppScreens.OperatorProfile.name) { ProfileScreen(navController, viewModel) }
        composable(AppScreens.ParkingLotDetail.name) { ParkingLotDetail(navController) }
        composable(AppScreens.MyActivityOperator.name) { MyActivityOperator(navController) }
        composable(AppScreens.CreateParking.name) { CreateParkingVisual() }
        composable(AppScreens.EditParking.name) { EditParkingVisual() }
        composable(AppScreens.ChatOp.name) { ChatOperador() }
        composable(AppScreens.ChatCli.name) { ChatCliente() }
    }

    LaunchedEffect(
        authState.isAuthenticated,
        authState.isVerified,
        authState.isLoading,
        authState.userRole
    ) {
        if (authState.isLoading) return@LaunchedEffect

        val currentRoute = navController.currentBackStackEntry?.destination?.route

        when {
            authState.isAuthenticated && !authState.isVerified -> {
                if (currentRoute != AppScreens.IdentityVerification.name) {
                    navController.navigate(AppScreens.IdentityVerification.name) { popUpTo(0) }
                }
            }

            authState.isAuthenticated && authState.isVerified -> {
                val destination = if (authState.userRole == "Operador") {
                    AppScreens.HomeOperator.name
                } else {
                    AppScreens.HomeUser.name
                }

                if (currentRoute != destination) {
                    navController.navigate(destination) { popUpTo(0) }
                }
            }

            !authState.isAuthenticated -> {
                if (currentRoute != AppScreens.LogIn.name && currentRoute != AppScreens.SignUp.name) {
                    navController.navigate(AppScreens.LogIn.name) { popUpTo(0) }
                }
            }
        }
    }
}