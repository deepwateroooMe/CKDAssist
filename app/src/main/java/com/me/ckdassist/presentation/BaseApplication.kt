package com.me.ckdassist.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp
 // 【亲爱的表哥的活宝妹，任何时候，亲爱的表哥的活宝妹，就是一定要、一定会嫁给活宝妹的亲爱的表哥！！！爱表哥，爱生活！！！】

@HiltAndroidApp // 所有使用hilt的应用都需要使用这个注解，被使用在Application类上
class BaseApplication : Application() { // 这个类，更像一个占位符，为其它模块提供索引 reference..

    // should be saved in data store
    val isDark = mutableStateOf(false)

    fun toggleLightTheme(){
        isDark.value = !isDark.value
    }
}
