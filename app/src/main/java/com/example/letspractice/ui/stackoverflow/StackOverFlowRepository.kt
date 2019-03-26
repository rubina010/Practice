package com.example.letspractice.ui.stackoverflow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.letspractice.ApiModule
import com.example.letspractice.database.AppDatabase
import com.example.letspractice.database.dao.StackFlowModelDao
import com.example.letspractice.entity.Items
import com.example.letspractice.entity.StackFlowResponse

class StackOverFlowRepository(app: Application) {
    var stackFlowModelDao: StackFlowModelDao

    init {
        val appDatabase = AppDatabase.getAppDatabase(app.applicationContext)
        stackFlowModelDao = appDatabase.StackFlowModelDao()
    }

    fun getDataFromApi(currentPage: Int) = ApiModule.newInstance().createApiService().getAllAnswers(currentPage, 20, "stackoverflow")
    fun insertDataToDb(items: StackFlowResponse) = stackFlowModelDao.insertALLAnswers(items)

    fun getDataFromDb(): LiveData<PagedList<StackFlowResponse>> {
        val factory = stackFlowModelDao.getAllAnswers()
        val builder = LivePagedListBuilder(factory, PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .build())
        return builder
                .setBoundaryCallback(StackOverFlowViewModel.StackOverFlowBoundaryCallback(this@StackOverFlowRepository))
                .build()

    }

    fun getAnswerCount() = stackFlowModelDao.getAnswerCount()

    fun delete() = stackFlowModelDao.deleteAll()
}