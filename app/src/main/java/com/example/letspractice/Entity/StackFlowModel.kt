package com.example.letspractice.Entity

data class StackFlowResponse(
        val items: List<Items>,
        val hasMore: Boolean,
        val quote_max: Int,
        val quota_remaining: Int
)

class Items(
        val owner: List<Owner>,
        val is_accepted: Boolean,
        val score: Int,
        val last_activity_date: Long,
        val last_edit_date: Long,
        val creation_date: Long,
        val answer_id: Long,
        val question_id: Long
)

class Owner(
        val reputaion: Int,
        val user_id: Long,
        val user_type: String,
        val accept_rate: Int,
        val profile_image: String,
        val display_image: String,
        val link: String
)