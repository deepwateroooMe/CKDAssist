package com.me.ckdassist.repository

import com.me.ckdassist.domain.model.Recipe
import com.me.ckdassist.domain.model.Topic
import com.me.ckdassist.network.TopicService
import com.me.ckdassist.network.model.TopicDtoMapper
import com.me.ckdassist.repository.TopicRepository

class TopicRepository_Impl (  // 下面暂时去掉，想过编译
    private val topicService: TopicService,
    private val mapper: TopicDtoMapper,
): TopicRepository {

    // override suspend fun search(token: String, page: Int, query: String): List<Topic>> {
    //     return mapper.toDomainList(topicService.search(token = token, page = page, query = query).topics)
    // }

    // override suspend fun get(token: String, id: Int): Topic { // 网搜：从网络上，把指定菜谱给抓下来、转为本应用的 DomainModel Topic
    //                          // 任何时候调用，都网搜，再下载一遍。因为应用、没有设置本地数据库之类的任何缓存
    //                          return mapper.mapToDomainModel(topicService.get(token = token, id))
    //                      }
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun get(token: String, id: Int): Topic {
        TODO("Not yet implemented")
    }
}