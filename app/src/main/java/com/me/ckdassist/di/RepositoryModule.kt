package com.me.ckdassist.di

import com.me.ckdassist.network.RecipeService
import com.me.ckdassist.network.model.RecipeDtoMapper
import com.me.ckdassist.repository.RecipeRepository
import com.me.ckdassist.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // 应用【脊梁骨、模块】： 应用启动时，hilt 自动注入的、三大模块，现在看懂了！！
@InstallIn(SingletonComponent::class) // 单例模块
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
            recipeService: RecipeService,
            recipeMapper: RecipeDtoMapper,
    ): RecipeRepository{
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}
