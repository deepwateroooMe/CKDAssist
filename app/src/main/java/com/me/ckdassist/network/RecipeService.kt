package com.me.ckdassist.network

import com.me.ckdassist.network.model.RecipeDto
import com.me.ckdassist.network.response.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// 申明接口类：它的实现类呢？神龙见首不见尾的。。。
// 【TODO】：这里还没理解，没能找到它人具体实现类，在哪里？应该就是在极简标签◎GET() 的自动实现。
interface RecipeService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeSearchResponse

    @GET("get")
    suspend fun get(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeDto // 返回：从网搜下来的、【json序列化】的 RecipeDto. 任何时候，都上网络下载，没缓存
}