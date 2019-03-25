package com.example.letspractice.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.letspractice.entity.StackFlowResponse


@Dao
interface StackFlowModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertALLAnswers(answers: StackFlowResponse)

    @Query("SELECT * FROM tbl_stackItems")
    fun getAllAnswers(): DataSource.Factory<Int, StackFlowResponse>
}