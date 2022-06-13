package com.asi.composenavigationtutorial.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asi.composenavigationtutorial.LocalNavController
import com.asi.composenavigationtutorial.model.UserBean
import com.asi.composenavigationtutorial.route.IntegralScreen
import com.asi.composenavigationtutorial.route.LoginScreen
import com.asi.composenavigationtutorial.route.ShopScreen

/**
 * @ClassName LoginPage.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月10日 14:26:00
 */

@Composable
fun HomePage(navController: NavController = LocalNavController.current) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "主页", Modifier.padding(top = 80.dp))
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            val userBean = UserBean("新垣结衣", 18)
            navController.navigate(ShopScreen.Product.argsRoute(userBean))
        }) {
            Text(text = "跳转到Product页")
        }
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.navigate(LoginScreen.Login.route)
        }) {
            Text(text = "跳转到登录页")
        }
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.navigate(ShopScreen.Shop.route)
        }) {
            Text(text = "跳转到Shop页")
        }
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            val userBean = UserBean("asi", 18)
            navController.navigate(IntegralScreen.Integral.route)
        }) {
            Text(text = "跳转到积分页")
        }
    }
}


@Preview
@Composable
fun PreviewHomePage() {
    HomePage(rememberNavController())
}


