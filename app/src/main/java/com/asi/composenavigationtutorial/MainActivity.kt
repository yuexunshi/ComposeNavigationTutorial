package com.asi.composenavigationtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asi.composenavigationtutorial.model.UserBean
import com.asi.composenavigationtutorial.model.UserNavType
import com.asi.composenavigationtutorial.page.*
import com.asi.composenavigationtutorial.route.NavGraph
import com.asi.composenavigationtutorial.ui.theme.ComposeNavigationTutorialTheme
import dagger.hilt.android.AndroidEntryPoint


val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController  provided!")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTutorialTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    NavGraph()
                }
            }
        }
    }
}

