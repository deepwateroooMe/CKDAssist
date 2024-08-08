package com.me.ckdassist.presentation.ui.topic

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.ckdassist.domain.model.Topic
import com.me.ckdassist.presentation.ui.topic.TopicEvent.GetTopicEvent
import com.me.ckdassist.repository.TopicRepository
import com.me.ckdassist.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_TOPIC = "topic.state.topic.key" // 它说，这是个、常量、永远不变的量. 可是后面，它的值是可以变的。。

@ExperimentalCoroutinesApi
@HiltViewModel
class TopicViewModel
@Inject // 自动注入的
constructor( // 基本全看懂了！！【亲爱的表哥的活宝妹，任何时候，亲爱的表哥的活宝妹，就是一定要、一定会嫁给活宝妹的亲爱的表哥！！！爱表哥，爱生活！！！】
    private val topicRepository: TopicRepository,  // 内部：幕后，是怎么自动生成的？？？【TODO】：
    @Named("auth_token") private val token: String,
    private val state: SavedStateHandle, // 亲爱的表哥的活宝妹，现在今年的亲爱的表哥的活宝妹，需要把MVVM 尤其是Kotlin 语言的这些幕后原理，全部弄懂
): ViewModel(){

    val topic: MutableState<Topic?> = mutableStateOf(null) // 一条菜谱
    val loading = mutableStateOf(false) // 标记：是否加载中的、状态变量

    init {
        // restore if process dies: 如果进程——本应用，被安卓系统给干掉了。。这里，如果应用是死在【单个菜谱页】，但是应用死前又不曾、又没能够缓存死前菜谱号，那就加载这个菜谱页。。
        state.get<Int>(STATE_KEY_TOPIC)?.let{ topicId ->
                  onTriggerEvent(GetTopicEvent(topicId)) // 去抓某个单个的菜谱：【TODO】：现在去检查，扒下来的菜谱，是否哪里缓存着缓存过？还是任何时候都永远网上去弄下来。。
              }
    }

    fun onTriggerEvent(event: TopicEvent) { // 视图上：用户点击操作、【自顶向下】，跳转页面至、单独的【菜谱页】
        viewModelScope.launch {
            try {
                when (event) {
                    is GetTopicEvent -> {
                        if (topic.value == null) { // 应该是：没有缓存过？
                            getTopic(event.id) // 去网络上抓菜谱：定义在本文件下面
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private suspend fun getTopic(id: Int) {
        loading.value = true 

        // simulate a delay to show loading
        delay(1000) // 语言协程里的函数

        val topic = topicRepository.get(token=token, id=id) // 从仓库里，抓出这个菜谱的数据
        this.topic.value = topic

        // 这里【自己加的】：restore if process dies: 如果进程——本应用，被安卓系统给干掉了。。这里，如果应用是死在【单个菜谱页】，这个常量STATE_KEY_TOPIC
        state.set(STATE_KEY_TOPIC, topic.id) // 更新状态：缓存、保存：【单个菜谱页】停留正在显示的菜谱身份证号。。以防应用被杀了，能够及时有效恢复，有数据可指引加载显示

        loading.value = false // 加载完毕
    }
} 