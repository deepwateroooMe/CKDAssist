package com.me.ckdassist.domain.model

// List<String> Info 显示的主要条目
data class Topic (
    val id: Int,
    val title: String,
    val featuredImage: String, // 可能会有1 图片，如果多张呢？
    val contents: List<String> = listOf()
)
