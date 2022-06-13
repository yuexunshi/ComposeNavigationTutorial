package com.asi.composenavigationtutorial.route

import com.asi.composenavigationtutorial.model.UserBean

/**
 * @ClassName Screen.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月13日 14:47:00
 */
const val DestinationArg = "arg"


sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
}

sealed class IntegralScreen(val route: String) {
    object Integral : Screen("integral_screen")
    object Root : Screen("integral_root_screen")
}

sealed class LoginScreen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Root : Screen("login_root_screen")
}

sealed class ShopScreen(val route: String) {
    object Shop : ShopScreen("shop_screen")
    object Product : ShopScreen("product_screen/{$DestinationArg}") {
        fun argsRoute(argument: UserBean) = "product_screen/$argument"
    }

    object Root : ShopScreen("shop_root_screen")
}