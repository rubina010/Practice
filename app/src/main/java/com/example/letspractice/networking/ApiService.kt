package com.example.letspractice.networking

import com.example.letspractice.entity.GitHubResponse
import com.example.letspractice.entity.StackFlowResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/everything/")
    fun getAllNews(@Query("q") q: String,
                   @Query("apiKey") key: String,
                   @Query("page") page: Long,
                   @Query("pageSize") pageSize: Int)

    @GET("answers")
    fun getAllAnswers(@Query("page") page: Int,
                      @Query("pagesize") pagesize: Int,
                      @Query("site") site: String): Flowable<StackFlowResponse>

    @GET("https://api.github.com/repositories")
    fun getAllRepos(@Query("page") page: Int,
                    @Query("per_page") per_page: Int): Flowable<GitHubResponse>
}