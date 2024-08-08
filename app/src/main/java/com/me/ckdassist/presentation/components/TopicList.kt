package com.me.ckdassist.ui.components
import android.nfc.tech.MifareUltralight.PAGE_SIZE  // <<<<<<<<<<<<<<<<<<<< ??
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.me.ckdassist.domain.model.Topic
import com.me.ckdassist.presentation.components.NothingHere
import com.me.ckdassist.ui.components.util.HorizontalDottedProgressBar
// import com.me.ckdassist.presentation.ui.recipe_list.PAGE_SIZE  // <<<<<<<<<<<<<<<<<<<< 这里要看进去， xml 里的变量？
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun TopicList(
    loading: Boolean,
    topics: List<Topic>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onNavigateToTopicDetailScreen: (Int) -> Unit,
){
    Box(modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && topics.isEmpty()) {
            HorizontalDottedProgressBar() // 加载中。。。
            //            LoadingTopicListShimmer(imageHeight = 250.dp,)
        } else if (topics.isEmpty()) {
            NothingHere() 
        } else {
            LazyColumn {
                itemsIndexed(
                    items = topics
                ) { index, topic ->
                    onChangeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        onTriggerNextPage()
                    }
                    // 如果需要弄出狠多个的话，可以把它们弄成 RecyclerView 里可点击项，就把它们制成固定格式的卡片。。
                    // TopicCard( // 没有明确的图卡，感觉不需要这个东西，至少目前 info 不需要它
                    //     topic = topic,
                    //     onClick = { onNavigateToTopicDetailScreen(topic.id)
                    //     }
                    // )
                }
            }
        }
    }
}
