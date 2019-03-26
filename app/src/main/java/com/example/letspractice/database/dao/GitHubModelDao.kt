package com.example.letspractice.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.letspractice.entity.GitHubResponse
import io.reactivex.Flowable

@Dao
interface GitHubModelDao {
    @Query("SELECT * FROM tbl_github")
    fun getAllRepos(): Flowable<GitHubResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repos: GitHubResponse)
}