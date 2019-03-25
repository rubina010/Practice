package com.example.letspractice.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_stackItems")
data class StackFlowResponse(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @ColumnInfo(name = "items")
        val items: List<Items>,
        @ColumnInfo(name = "hasMore")
        val hasMore: Boolean,
        @ColumnInfo(name = "quota_max")
        val quote_max: Int,
        @ColumnInfo(name = "quota_remaining")
        val quota_remaining: Int
)

class Items(
        @Embedded
        val owner: Owner,
        @ColumnInfo(name = "is_accepted")
        val is_accepted: Boolean,
        @ColumnInfo(name = "score")
        val score: Int,
        @ColumnInfo(name = "last_activity_date")
        val last_activity_date: Long,
        @ColumnInfo(name = "last_edit_date")
        val last_edit_date: Long,
        @ColumnInfo(name = "creation_date")
        val creation_date: Long,
        @ColumnInfo(name = "answer_id")
        val answer_id: Long,
        @ColumnInfo(name = "question_id")
        val question_id: Long
)

class Owner(
        val reputaion: Int,
        @ColumnInfo(name = "user_id")
        val user_id: Long,
        val user_type: String,
        val accept_rate: Int,
        @ColumnInfo(name = "profile_image")
        val profile_image: String,
        @ColumnInfo(name = "display_name")
        val display_name: String,
        val link: String
)