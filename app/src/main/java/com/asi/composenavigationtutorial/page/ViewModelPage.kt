package com.asi.composenavigationtutorial.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.asi.composenavigationtutorial.model.UserBean

/**
 * @ClassName ViewModelPage.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月10日 18:00:00
 */


@Composable
fun  ViewModelPage (navController: NavController, userBean: UserBean?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "用户页", Modifier.padding(top = 80.dp))
        Text(text = "用户名字=${userBean?.name}", Modifier.padding(top = 80.dp))

        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.navigate("home") {

                popUpTo("home") {
                    inclusive = true
                }
            }
        }) {
            Text(text = "返回主页")
        }
    }
}

class  MyViewModel:ViewModel(){
    var user= mutableStateOf<UserBean>(UserBean("",12))



}