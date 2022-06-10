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

/**
 * @ClassName LoginPage.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月10日 14:26:00
 */


@Composable
fun LoginPage(navController: NavController) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {

        Text(text = "登录页", Modifier.padding(top = 80.dp))

        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.navigate("home")
        }) {
            Text(text = "跳转到home")
        }

    }

}


@Preview
@Composable
fun PreviewLoginPage() {
    LoginPage(rememberNavController())
}


