package com.asi.composenavigationtutorial.route

/**
 * @ClassName Screen.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月13日 14:47:00
 */
const val DestinationArg = "arg"


sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Shop : Screen("shop_screen")
    object Product : Screen("product_screen/{$DestinationArg}")
    // 积分
    object Integral : Screen("integral_screen")
}