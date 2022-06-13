package com.asi.composenavigationtutorial.route

import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asi.composenavigationtutorial.page.HomePage
import com.asi.composenavigationtutorial.page.LoginPage

/**
 * @ClassName NavHost.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月13日 14:53:00
 */
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(route = Screen.Login.route) {
            LoginPage(navController)
        }
    }


}