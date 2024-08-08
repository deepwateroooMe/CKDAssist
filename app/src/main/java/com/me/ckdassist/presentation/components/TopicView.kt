package com.me.ckdassist.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.me.ckdassist.domain.model.Topic
// import com.me.ckdassist.util.DEFAULT_RECIPE_IMAGE
import com.me.ckdassist.util.DEFAULT_TOPIC_IMAGE
import com.me.ckdassist.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun TopicView(
    topic: Topic,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item { // 先，加载，菜谱成品图片
            val image = loadPicture(url = topic.featuredImage, defaultImage = DEFAULT_TOPIC_IMAGE).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "Topic Featured Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        // .height(IMAGE_HEIGHT.dp) // 【TODO】：这个不知道哪里定义的，先去掉
                     ,
                    contentScale = ContentScale.Crop,
                )
            }
            Column( // 再：菜谱原材料、用量、细节
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(modifier = Modifier // title+ Rank
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ){
                    Text(
                        text = topic.title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start)
                        ,
                        style = MaterialTheme.typography.h3
                    )
                    // val rank = topic.rating.toString()
                    // Text(
                    //     text = rank,
                    //     modifier = Modifier
                    //         .fillMaxWidth()
                    //         .wrapContentWidth(Alignment.End)
                    //         .align(Alignment.CenterVertically)
                    //     ,
                    //     style = MaterialTheme.typography.h5
                    // )
                }
                // val updated = topic.dateUpdated
                // Text(
                //     text = "Updated ${updated} by ${topic.publisher}"
                //     ,
                //     modifier = Modifier
                //         .fillMaxWidth()
                //         .padding(bottom = 8.dp)
                //     ,
                //     style = MaterialTheme.typography.caption
                // )
                for (content in topic.contents){
                    Text(
                        text = content,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                        ,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}