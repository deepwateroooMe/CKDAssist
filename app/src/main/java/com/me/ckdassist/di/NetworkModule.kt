package com.me.ckdassist.di

import com.me.ckdassist.network.RecipeService
import com.me.ckdassist.network.model.RecipeDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module // 应用【脊梁骨、模块】： hilt 自动注入。。
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton // 单例
    @Provides
    fun provideRecipeMapper(): RecipeDtoMapper {
        return RecipeDtoMapper() // 就是 new 一个新的。。
    }

    // 单例：这里会自动注入，生成一个 RecipeService 的、单例实例服务。感觉像是、就是，RecipeService 的真正实现类了一样！！
    @Singleton 
    @Provides
    fun provideRecipeService(): RecipeService {
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/") // 从这个网站上扒下来的，没有标签、想要添加标签的话：可能得自己解析关键字，并手动添加标签了。。
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)
    }

    /**
     * I might include proper authentication later on food2fork.ca
     * For now just hard code a token.
     */
    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String{
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}