package com.asi.composenavigationtutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asi.composenavigationtutorial.model.UserBean
import com.asi.composenavigationtutorial.model.UserNavType
import com.asi.composenavigationtutorial.page.*
import com.asi.composenavigationtutorial.ui.theme.ComposeNavigationTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTutorialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginPage(navController) }
                    composable("home") { HomePage(navController) }
                    composable("list") { ListPage(navController) }
                    composable("details") { DetailsPage(navController) }
                    composable("user/{userId}",
                        arguments = listOf(navArgument("userId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val string = backStackEntry.arguments?.getString("userId")

                        UserPage(navController, string)
                    }

                    composable(
                        "user_bean",
                    ) {
                        // 直接从 previousBackStackEntry 中提取参数
                        var userBean =
                            navController.previousBackStackEntry?.arguments?.getParcelable<UserBean>(
                                "userBean")
                        UserBeanPage(navController, userBean)
                    }
                    composable(
                        "navtype/{user}",
                        arguments = listOf(navArgument("user") { type = UserNavType() })
                    ) {
                        val userBean = it.arguments?.getParcelable<UserBean>("user")
                        NavTypePage(navController, userBean)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeNavigationTutorialTheme {
        Greeting("Android")
    }
}