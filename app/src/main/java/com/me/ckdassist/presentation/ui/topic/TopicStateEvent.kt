package com.me.ckdassist.presentation.ui.topic

sealed class TopicEvent {

    data class GetTopicEvent(
        val id: Int
    ): TopicEvent()
}
