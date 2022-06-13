package com.asi.composenavigationtutorial.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.asi.composenavigationtutorial.LocalNavController
import com.asi.composenavigationtutorial.model.UserNavType
import com.asi.composenavigationtutorial.page.*

/**
 * @ClassName NavHost.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月13日 14:53:00
 */
@Composable
fun NavGraph() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomePage()
        }
        shopGraph()
        loginGraph()
        integralGraph()
    }

}

fun NavGraphBuilder.shopGraph() {
    navigation(
        startDestination = ShopScreen.Shop.route,
        route = ShopScreen.Root.route
    ) {
        composable(route = ShopScreen.Shop.route) {
            ShopPage()
        }
        composable(route = ShopScreen.Product.route,
            arguments = listOf(navArgument(DestinationArg) { type = UserNavType() })
        ) {
            ProductPage()
        }
    }
}
fun NavGraphBuilder.loginGraph() {
    navigation(
        startDestination = LoginScreen.Login.route,
        route = LoginScreen.Root.route
    ) {
        composable(route = LoginScreen.Login.route) {
            LoginPage()
        }
        composable(route = LoginScreen.Register.route) {
            RegisterPage()
        }
    }

}


fun NavGraphBuilder.integralGraph() {
    navigation(
        startDestination = IntegralScreen.Integral.route,
        route = IntegralScreen.Root.route
    ) {
        composable(route = IntegralScreen.Integral.route) {
            IntegralPage()
        }
    }
}