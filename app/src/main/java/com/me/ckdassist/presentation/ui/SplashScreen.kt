package com.me.ckdassist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.me.ckdassist.R

@Composable
fun SplashScreen(name: String) {
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image( // 【TODO】：添加应用图片，双肾图片 Glide 图库索引添加. 图片需要放大，晚点儿再弄. 动漫。。这个需要一点儿动画效应
            painter = painterResource(id = R.drawable.applogo),
            contentDescription = "Renal Disease"
        )
        Text(text = "Hi there, you or your beloved ones, diagnosed CKD, or self awared CKD?",
             style = MaterialTheme.typography.h6,
             modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(text = "I am sorry to know.", 
             style = MaterialTheme.typography.body1
        )
        Text(text = "But you are NOT NOT NOT alone !!!", 
             style = MaterialTheme.typography.body1
        )
        Text(text = "I myself am similar as you are, self awared CKD", 
             style = MaterialTheme.typography.body1
        )
        Text(text = "I designed and implemented this app for us!", 
             style = MaterialTheme.typography.body1
        )
        Text(text = " ",  // 想添加空行
             style = MaterialTheme.typography.body1
        )
        Text(text = "We.e.e.e...,", 
             style = MaterialTheme.typography.body1
        )
        Text(text = "Stay together,", 
             style = MaterialTheme.typography.body1
        )
        Text(text = "Fight together!", 
             style = MaterialTheme.typography.body1
        )
        Text(text = " ",  // 想添加空行
             style = MaterialTheme.typography.body1
        )
        Text(text = "Together,", 
             style = MaterialTheme.typography.body1
        )
        Text(text = "We.e.e.e... live better, survive better !!!", 
             style = MaterialTheme.typography.body1
        )
    }
}


@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen("aaa")
}
