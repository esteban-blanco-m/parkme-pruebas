package com.example.parkme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parkme.screens.ChatCliente
import com.example.parkme.screens.ChatOperador
import com.example.parkme.screens.CreateParkingVisual
import com.example.parkme.screens.EditParkingVisual
import com.example.parkme.screens.HomeOperator
import com.example.parkme.screens.LogIn
import com.example.parkme.screens.HomeUser
import com.example.parkme.screens.SignUp
import com.example.parkme.screens.IdentityVerification
import com.example.parkme.screens.MyActivityOperator
import com.example.parkme.screens.MyActivity
import com.example.parkme.screens.MyActivityOperator
import com.example.parkme.screens.OperatorProfile
import com.example.parkme.screens.ParkingLotDetail
import com.example.parkme.screens.RateParkingLot
import com.example.parkme.screens.SearchFilters
import com.example.parkme.screens.SearchMap
import com.example.parkme.screens.UserProfile

enum class AppScreens {
    HomeUser,
    LogIn,
    SignUp,
    IdentityVerification,
    SearchMap,
    SearchFilters,
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

    NavHost(
        navController = navController,
        startDestination = com.example.parkme.navigation.AppScreens.LogIn.name
    ) {
        composable(route = AppScreens.LogIn.name) {
            LogIn(navController)
        }
        composable(route = AppScreens.HomeUser.name) {
            HomeUser(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.SignUp.name) {
            SignUp(navController)
        }
        composable("${AppScreens.IdentityVerification.name}/{role}") { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role")

            IdentityVerification(navController = navController, selectedRole = role)
        }
        composable(route = com.example.parkme.navigation.AppScreens.SearchMap.name) {
            SearchMap(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.SearchFilters.name) {
            SearchFilters()
        }
        composable(route = com.example.parkme.navigation.AppScreens.RateParkingLot.name) {
            RateParkingLot(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.MyActivity.name) {
            MyActivity(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.UserProfile.name) {
            UserProfile(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.OperatorProfile.name) {
            OperatorProfile(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.ParkingLotDetail.name) {
            ParkingLotDetail(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.HomeOperator.name) {
            HomeOperator(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.MyActivityOperator.name) {
            MyActivityOperator(navController)
        }
        composable(route = com.example.parkme.navigation.AppScreens.CreateParking.name) {
            CreateParkingVisual()
        }
        composable(route = com.example.parkme.navigation.AppScreens.EditParking.name) {
            EditParkingVisual()
        }
        composable(route = com.example.parkme.navigation.AppScreens.ChatOp.name) {
            ChatOperador()
        }
        composable(route = com.example.parkme.navigation.AppScreens.ChatCli.name) {
            ChatCliente()
        }
        

    }
}