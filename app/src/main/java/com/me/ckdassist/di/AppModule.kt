package com.me.ckdassist.di

import android.content.Context
import com.me.ckdassist.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

 // 【TODO】：要搞明白，这个，在应用的幕后，或是被 hilt 库，是怎么用的？
 // 【TODO】：这里，安卓， Hilt 不知道为什么，亲爱的表哥的活宝妹的应用运行时， hilt 像是没能生成Hilt_BaseApplication 类？
@Module
@InstallIn(SingletonComponent ::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }
}
