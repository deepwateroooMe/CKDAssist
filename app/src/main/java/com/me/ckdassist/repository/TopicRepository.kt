package com.me.ckdassist.repository

import com.me.ckdassist.domain.model.Recipe
import com.me.ckdassist.domain.model.Topic

interface TopicRepository { // 接口
    // 一个是：网搜，后显示，各菜谱链条
    // suspend fun search(token: String, page: Int, query: String): List<com.me.ckdassist.domain.model.Recipe>
    suspend fun search(token: String, page: Int, query: String): List<Recipe>
    // 一个是：网搜，后显示：单个菜谱、供读阅
    suspend fun get(token: String, id: Int): Topic
}

