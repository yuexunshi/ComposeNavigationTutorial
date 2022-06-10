package com.asi.composenavigationtutorial.page

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asi.composenavigationtutorial.model.UserBean
import com.google.gson.Gson

/**
 * @ClassName LoginPage.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月10日 14:26:00
 */


@Composable
fun HomePage(navController: NavController) {

    SideEffect {
        Log.e("asi", "开始打印路由堆栈，共有${navController.backQueue.size}个")
        navController.backQueue.forEach {
            val route = it.destination.route
            Log.e("asi", "路由堆栈:$route==${it.id} ")
        }
        Log.e("asi", "打印路由堆栈结束}")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "主页", Modifier.padding(top = 80.dp))
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            val userBean = UserBean("新垣结衣", 18)
            val json = Uri.encode(Gson().toJson(userBean))
            navController.popBackStack()
            navController.navigate("navtype/$json")
        }) {
            Text(text = "跳转到NavType页")
        }
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            val userBean = UserBean("新垣结衣", 18)
            navController.currentBackStackEntry?.arguments?.putParcelable("userBean", userBean)
            navController.popBackStack()
            navController.navigate("user_bean")
        }) {
            Text(text = "跳转到新垣结衣用户页")
        }
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            val userBean = UserBean("asi", 18)
            navController.currentBackStackEntry?.arguments?.putParcelable("userBean", userBean)
            navController.navigate("user_bean")
        }) {
            Text(text = "传递bean")
        }
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.navigate("user/123")
        }) {
            Text(text = "跳转到用户页")
        }
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.navigate("list")
        }) {
            Text(text = "跳转到列表页")
        }
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.popBackStack()
        }) {
            Text(text = "返回")
        }
    }
}


@Preview
@Composable
fun PreviewHomePage() {
    HomePage(rememberNavController())
}


