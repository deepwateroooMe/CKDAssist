package com.me.ckdassist.presentation.ui.topic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.me.ckdassist.presentation.BaseApplication
import com.me.ckdassist.presentation.components.CircularIndeterminateProgressBar
import com.me.ckdassist.presentation.components.DefaultSnackbar
import com.me.ckdassist.presentation.components.LoadingRecipeShimmer
import com.me.ckdassist.presentation.components.TopicView
import com.me.ckdassist.presentation.theme.AppTheme
import com.me.ckdassist.presentation.ui.topic.TopicEvent.GetTopicEvent
import com.me.ckdassist.presentation.components.util.SnackbarController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

const val IMAGE_HEIGHT = 260

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TopicFragment: Fragment() {
    @Inject
    lateinit var application: BaseApplication // 拿一个应用级别上下文引用。。
    private val snackbarController = SnackbarController(lifecycleScope) // 【TODO】：还没弄明白，这个 snackbar 是干什么的、有什么用
    private val viewModel: TopicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) { // 单独的菜谱页
        super.onCreate(savedInstanceState)
        arguments?.getInt("topicId")?.let { topicId -> // arguments：不知道这个变量，是什么意思【TODO】：
            viewModel.onTriggerEvent(GetTopicEvent(topicId))
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val loading = viewModel.loading.value
                val topic = viewModel.topic.value
                val scaffoldState = rememberScaffoldState()
                AppTheme(
                    displayProgressBar = loading,
                    scaffoldState = scaffoldState,
                    darkTheme = application.isDark.value,
                ){
                    Scaffold(
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ) {
                        Box (
                            modifier = Modifier.fillMaxSize()
                        ){
                            if (loading && topic == null) // 正忙加载中：显示友好加载中、图标标记。。。
                                LoadingRecipeShimmer(imageHeight = IMAGE_HEIGHT.dp)
                            else topic?.let {
                                     if (it.id == 1) { // force an error to demo snackbar
                                         snackbarController.getScope().launch {
                                             snackbarController.showSnackbar(
                                                 scaffoldState = scaffoldState,
                                                 message = "An error occurred with this topic",
                                                 actionLabel = "Ok"
                                             )
                                         }
                                     }
                                     else {
                                         TopicView( // 加载：单条菜谱页
                                             topic = it,
                                         )
                                     }
                                 }
                            CircularIndeterminateProgressBar(isDisplayed = loading, verticalBias = 0.3f)
                            DefaultSnackbar(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                onDismiss = {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }
                }
            }
        }
    }
}