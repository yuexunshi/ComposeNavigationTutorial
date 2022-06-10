# 使用 Compose 进行导航

 [Jetpack 应用架构指南](https://developer.android.com/jetpack/guide?hl=zh-cn)推荐的[Navigation 组件](https://developer.android.com/guide/navigation?hl=zh-cn)，同样支持 Compose 应用，我们可以在利用 Navigation 组件的基础架构和功能的同时，在可组合项之间导航。



## 理解 Jetpack Compose 导航

它由 Composable Function 组成，允许我们在 Composable Views 之间进行切换。

它可以用来替换 Activity → Activity 或 Fragment → Fragment 的跳转。



## 设置

首先在应用模块( Module )的 `build.gradle` 文件中添加以下依赖项：

```
dependencies {
   def nav_version = "2.4.2"

    implementation "androidx.navigation:navigation-compose:$nav_version"
}
```

## 使用

### 创建 NavController

`NavController` 是 Navigation 组件的中心 API。此 API 是有状态的，记录了每个**可组合屏幕**组成的堆栈和状态。

> `NavController` 相当于 `FragmentManager` ，可组合屏幕相当于 `Fragment`。

### 

```kotlin
val navController = rememberNavController()
```

遵循[状态提升](https://developer.android.com/jetpack/compose/state?hl=zh-cn#state-hoisting)的原则，建议在顶层可组合项中创建`NavController`，这样每个可组合项都能访问。

### 创建 NavHost

每个`NavHost`都需要一个`NavController`与之关联。在可组合项之间进行导航时，`NavHost` 的内容会自动进行[重组](https://developer.android.com/jetpack/compose/mental-model?hl=zh-cn#recomposition)。

```kotlin
NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginPage() }
        composable("home") { HomePage() }
}
```

`startDestination`参数是起始目的地的路由，也就是进入 Compose 应用的第一屏路由。里面是构建导航路线图的表达式，需要通过`NavGraphBuilder.composable`扩展函数注册路由。 

这里创建了2个页面：

```
@Composable
fun HomePage(navController: NavController) {
}
@Composable
fun LoginPage(navController: NavController) {
}
```

在登录页点击跳转到主页，而跳转需要 NavController ，所以在两个可组合项页面的构造里加上 NavController 参数。

## 导航到可组合项

```
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
```

`navController.navigate("home")`跳转到 NavHost 里注册的`home`路由对应的页面。

而在主页，同样也有一个按钮，点击返回：

```
@Composable
fun HomePage(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "主页", Modifier.padding(top = 80.dp))
        Button(modifier = Modifier.padding(top = 80.dp), onClick = {
            navController.popBackStack()
        }) {
            Text(text = "返回")
        }
    }
}
```

 `navController.popBackStack()`是弹出当前可组合项路由，也就是返回上一级。

![1](/Users/apple/Desktop/1.gif)

## 导航配置

主页通常是唯一的，登录->主页->列表->详情->主页,我们希望还是回到主页，并清除主页之上所有的路由。先走一遍流程，回到主页打印出路由堆栈：

```
    SideEffect {
        Log.e("asi", "开始打印路由堆栈，共有${navController.backQueue.size}个")
        navController.backQueue.forEach {
            val route = it.destination.route
            Log.e("asi", "路由堆栈:$route==${it.id} ")
        }
        Log.e("asi", "打印路由堆栈结束}")
    }
```

```
开始打印路由堆栈，共有6个
路由堆栈:null==bb9f715e-5d0d-4e43-950e-95a80659b56c 
路由堆栈:login==13fe251d-af7e-4ccd-8b8b-5152aca18a83 
路由堆栈:home==23c3f899-4723-41a7-bdea-0173e57212aa 
路由堆栈:list==f12c1b49-0256-44d0-beb3-66a0fd7aee1c 
路由堆栈:details==89e0fcf1-5311-4841-874a-220f4ef3ab67
路由堆栈:home==6eb2e460-21f4-4814-8b06-9bc6b5e2f042 
打印路由堆栈结束}
```

从 login 开始，最后回到主页，发现此时有两个 home ，id 并不相同，说明最后的导航新建了一个主页。

在详情页修改导航：

```
navController.navigate("home"){
    popUpTo("home")
}
```

打印：

```
开始打印路由堆栈，共有4个
路由堆栈:null==f596944e-bc1c-4015-84c2-4cac363b5c67 
路由堆栈:login==7f9ef6e2-c342-4b67-b4ec-41057ed16944
路由堆栈:home==e778a4a8-bc78-4b95-a309-633da17be7d9 
路由堆栈:home==1c2ee9d4-a712-4884-aaee-4d1ffd74c75a 
打印路由堆栈结束}
```

主页之上的路由清除了，但是还是出现了2个主页。因为`popUpTo`是弹出到给定的路由，把路由栈里路由弹出去，直到遇到了主页，就停止弹出了。此时栈里还有一个主页，这时候通过导航又向栈中压入一个主页，所以出现了2个。

添加`inclusive`：

```
navController.navigate("home"){
    popUpTo("home"){
        inclusive=true
    }
}
```

`inclusive`顾名思义，包括。是指弹出路由的时候，遇到了指定的 home ，也会把 home 弹出去才停止。我们再打印：

```
开始打印路由堆栈，共有3个
路由堆栈:null==2d569ae3-5289-49d9-827a-b3bcccbe4189 
路由堆栈:login==4ac17185-0ef2-474e-988e-0f2d158d66ef 
路由堆栈:home==658b85e7-b339-4ae2-adb3-1f9e61103446 
打印路由堆栈结束}
```

达到了要求。但是还有一种更简单的方法，当栈里路由弹到主页停止时，也就是主页在栈顶的时候，添加栈顶单例模式，就不再把主页再次压入栈中：

```
navController.navigate("home"){
    popUpTo("home")
    launchSingleTop=true
}
```

注意区别：第一种是弹出了旧的主页，压入一个新的主页。第二种还是原来的主页。

## 传递基本类型参数

新建一个用户页，传递 userId ，注册路由，先在路由名上添加占位符`user/{userId}`,然后添加参数并指定类型，默认是字符串类型。然后通过`backStackEntry.arguments?.getString("userId")`获取参数。

```
composable("user/{userId}",
    arguments = listOf(navArgument("userId") { type = NavType.StringType })
) {backStackEntry->
    val string = backStackEntry.arguments?.getString("userId")
    UserPage(navController,string) }
```

导航：

```
Button(modifier = Modifier.padding(top = 80.dp), onClick = {
    navController.navigate("user/123")
}) {
    Text(text = "跳转到用户页")
}
```

![2](/Users/apple/Desktop/2.gif)

## 传递对象

对象需要序列化后传递，首先添加序列化插件`kotlin-parcelize`：

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
}
```

```
@Parcelize
data class UserBean(val name:String,val age:Int):Parcelable
```

序列化类型的路由声明不需要占位符：

```
composable(
    "user_bean",
) 
```

然后从跳转过来的路由栈获取参数：

```
{
    // 直接从 previousBackStackEntry 中提取参数
    var userBean =
        navController.previousBackStackEntry?.arguments?.getParcelable<UserBean>(
            "userBean")
    UserBeanPage(navController, userBean)
}
```

导航：

```
Button(modifier = Modifier.padding(top = 80.dp), onClick = {
    val userBean=UserBean("asi",18)
    navController.currentBackStackEntry?.arguments?.putParcelable("userBean", userBean)
    navController.navigate("user_bean")
}) {
    Text(text = "传递bean")
}
```

![3](/Users/apple/Desktop/3.gif)

在实操中，我们一般会在跳转下一个页面的同时，关闭当前页面。这时，这种方法就失败了：

```
Button(modifier = Modifier.padding(top = 80.dp), onClick = {
    val userBean = UserBean("新垣结衣", 18)
    navController.currentBackStackEntry?.arguments?.putParcelable("userBean", userBean)
    navController.popBackStack()
    navController.navigate("user_bean")
}) {
    Text(text = "跳转到新垣结衣用户页")
}
```

![4](/Users/apple/Desktop/4.gif)

what？

在`navController.popBackStack()`的时候，弹出了当前栈，`navController.currentBackStackEntry?.arguments?.putParcelable()`设置的内容就没有了。如果需要安全的传递参数，需要用到自定义`NavType`。

## 自定义 NavType 传递参数

自定义类型需要把 Json 字符串转化为对应类型，这里使用[Gson](https://github.com/google/gson)，所以添加依赖：

```
  implementation 'com.google.code.gson:gson:2.9.0'
```

首先为要传递的参数定义一个 NavType ：

```
class UserNavType : NavType<UserBean>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): UserBean? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): UserBean {
        return Gson().fromJson(value, UserBean::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: UserBean) {
        bundle.putParcelable(key, value)
    }
}
```

然后注册路由添加占位符，并指定参数类型为自定义类型：

```
composable(
    "navtype/{user}",
    arguments = listOf(navArgument("user") { type = UserNavType() })
)
```

获取参数：

```
composable(
    "navtype/{user}",
    arguments = listOf(navArgument("user") { type = UserNavType() })
) {
    val userBean = it.arguments?.getParcelable<UserBean>("user")
    NavTypePage(navController, userBean)
}
```

导航：

```
Button(modifier = Modifier.padding(top = 80.dp), onClick = {
    val userBean = UserBean("新垣结衣", 18)
    val json = Uri.encode(Gson().toJson(userBean))
    navController.popBackStack()
    navController.navigate("navtype/$json")
}) {
    Text(text = "跳转到NavType页")
}
```

![5](/Users/apple/Desktop/5.gif)

## 在 ViewModel 中传递参数

在实际开发中，遵照 Mvi 架构，需要在 Viewmodel 获取参数并使用，而有构造参数的 ViewModel 需要实现 ViewModelProvider.AndroidViewModelFactory，增加大量样本代码，所以并不方便。我们可以借助 Hilt 和 SavedStateHandle 优雅的实现。

依赖 Hilt：

首先，将 `hilt-android-gradle-plugin` 插件添加到项目的根级 `build.gradle` 文件中：

```
buildscript {
    ext {
        compose_version = '1.1.0-beta01'
        hilt_version = '2.41'

    }
    dependencies {
        classpath "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
    }

}
```

然后，在 `app/build.gradle` 文件中添加以下依赖项：

```
//hilt
implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
implementation "com.google.dagger:hilt-android:$hilt_version"
kapt "com.google.dagger:hilt-android-compiler:$hilt_version"
kapt 'androidx.hilt:hilt-compiler:1.0.0'
```

同时添加插件：

```
id 'kotlin-kapt'
id 'dagger.hilt.android.plugin'
```

https://hitherejoe.medium.com/nested-navigation-graphs-in-jetpack-compose-dc0ada1d4726

https://medium.com/androiddevelopers/animations-in-navigation-compose-36d48870776b