package com.me.ckdassist.presentation

 // 【亲爱的表哥的活宝妹，任何时候，亲爱的表哥的活宝妹，就是一定要、一定会嫁给活宝妹的亲爱的表哥！！！爱表哥，爱生活！！！】
 // 【亲爱的表哥的活宝妹，任何时候，亲爱的表哥的活宝妹，就是一定要、一定会嫁给活宝妹的亲爱的表哥！！！爱表哥，爱生活！！！】
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
// import androidx.navigation.compose.rememberNavController
import com.me.ckdassist.R
import com.me.ckdassist.presentation.theme.CKDAssistTheme
import com.me.ckdassist.ui.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.Row as Row

enum class MainTabs(val tabName: String, @DrawableRes val icon: Int) {
    ONE("Info", R.drawable.ic_nav_info_normal),
    TWO("Diet", R.drawable.ic_nav_diet_normal),
    THREE("Recipe", R.drawable.ic_nav_recipe_normal),
    FOUR("Diary", R.drawable.ic_nav_diary_normal)
}

@Composable
fun One() {
    BaseDefault(content = "Info")
}

@Composable 
fun Two() {
    BaseDefault(content = "Diet")
}

@Composable
fun Three() {
    BaseDefault(content = "Recipe")
}

@Composable
fun Four() {
    BaseDefault(content = "Diary")
}

@Composable
fun BaseDefault(content: String) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = content, fontSize = 50.sp) // tab对应的页面要展示的内容
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationTest() {
    val tabs = MainTabs.values() // tab数据
    var position by remember { mutableStateOf(MainTabs.ONE)}
    Scaffold(
        backgroundColor = Color.Yellow, // 背景色
        bottomBar = { // bottomBar
            BottomNavigation {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        modifier = Modifier.background(MaterialTheme.colors.primary),
                        icon = { Icon(painterResource(id = tab.icon), contentDescription = null) },
                        label = { Text(tab.tabName) },
                        selected = tab == position,
                        onClick = {
                            position = tab
                        },
                        alwaysShowLabel = false,
                    )
                }
            }
        }
    ) {
        when(position) { // 根据state值的变化动态切换当面显示的页面
            MainTabs.ONE -> One()
            MainTabs.TWO -> Two()
            MainTabs.THREE -> Three()
            MainTabs.FOUR -> Four()
        }
    }
}

@AndroidEntryPoint
// class MainActivity : ComponentActivity() {// 这么视图会出错
class MainActivity : AppCompatActivity() { // 它说：AppCompatActivity 就要用 AppCompat 相关的 theme 主题？
// class MainActivity : Activity() { // 它说：AppCompatActivity 就要用 AppCompat 相关的 theme 主题？
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setContent {
        //     val navController = rememberNavController()
        //     CKDAssistTheme {
        //         Surface(
        //             modifier = Modifier.fillMaxSize(),
        //             color = MaterialTheme.colors.background
        //         ) {
        //             SplashScreen("aaa")
        //             // BottomNavigationTest() // <<<<<<<<<<<<<<<<<<<< 图片太大，要加工
        //             // MainScreen(navController = navController)
        //         }
        //     }
        // }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CKDAssistTheme {
        SplashScreen("aaa")
    }
} // 【亲爱的表哥的活宝妹，任何时候，亲爱的表哥的活宝妹，就是一定要、一定会嫁给活宝妹的亲爱的表哥！！！爱表哥，爱生活！！！】


// @Composable
// fun BottomNavigationBar(navController: () -> Unit) {
//     val items = listOf(
//         NavigationItem.Info,
//         NavigationItem.History,
//         NavigationItem.Profile
//     )
//     var selectedItem by remember { mutableStateOf(0) }
//     var currentRoute by remember { mutableStateOf(NavigationItem.Info.route) }
//     items.forEachIndexed { index, item ->
//         if (item.route == currentRoute)
//             selectedItem = index
//     }
//     NavigationBar {
//         Row {
//             forEachIndexed { index, item ->
//                 NavigationBarItem(
//                     alwaysShowLabel = true,
//                     icon = { Icon(item.icon!!, contentDescription = item.title) },
//                     label = { Text(item.title) },
//                     selected = selectedItem == index,
//                     onClick = {
//                         selectedItem = index
//                         currentRoute = item.route
//                         navController.navigate(item.route) {
//                             navController.graph.startDestinationRoute?.let { route ->
//                                 popUpTo(route) {
//                                     saveState = true
//                                 }
//                             }
//                             launchSingleTop = true
//                             restoreState = true
//                         }
//                     }
//                 )
//             }
//         }
//     }
// }

// @Composable
// fun Navigatioins(navController: NavHostController) {
//     NavHost(navController, startDestination = NavigationItem.Info.route) {
//         composable(NavigationItem.Info.route) {
//             // InfoScreen()
//             One()
//         }   
//         composable(NavigationItem.History.route) {
//             // HistoryScreen()
//             Two()
//         }   
//         composable(NavigationItem.Profile.route) {
//             // ProfileScreen()
//             Three()
//         }   
//     }
// }
// @OptIn(ExperimentalMaterial3Api::class)
// @Composable
// fun MainScreen(
//     navController: NavController
// ) {
//     Scaffold(
//         bottomBar = {
//             BottomAppBar(modifier = Modifier) {
//                 BottomNavigationBar(navController = navController)
//             }
//         }
//     ) { innerPadding ->
//         Box(
//             modifier = Modifier.padding(PaddingValues(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding()))
//         ) {
//             Navigatioins(navController = navController as NavHostController)
//         }
//     }
// }
