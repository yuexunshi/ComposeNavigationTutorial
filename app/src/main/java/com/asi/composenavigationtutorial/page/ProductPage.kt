package com.asi.composenavigationtutorial.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.asi.composenavigationtutorial.LocalNavController
import com.asi.composenavigationtutorial.model.UserBean
import com.asi.composenavigationtutorial.route.DestinationArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @ClassName LoginPage.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月10日 14:26:00
 */


@Composable
fun ProductPage(viewModel: MyViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val navController = LocalNavController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "商品详情页", Modifier.padding(top = 80.dp))
        Text(text = "用户名字=${state.userBean?.name}", Modifier.padding(top = 80.dp))

        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.popBackStack()
        }) {
            Text(text = "返回主页")
        }
    }
}


@HiltViewModel
class MyViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel(
) {
    private val _userBean: UserBean? = savedStateHandle.get<UserBean>(DestinationArg)
    val state = mutableStateOf(State(
        _userBean
    ))

}

data class State(val userBean: UserBean?)
