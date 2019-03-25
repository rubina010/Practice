package com.example.letspractice.ui.stackoverflow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.letspractice.ApiModule
import com.example.letspractice.entity.StackFlowResponse
import com.example.letspractice.database.AppDatabase
import com.example.letspractice.database.dao.StackFlowModelDao
import timber.log.Timber

class StackOverFlowRepository(app: Application) {
    var stackFlowModelDao: StackFlowModelDao

    init {
        val appDatabase = AppDatabase.getAppDatabase(app.applicationContext)
        stackFlowModelDao = appDatabase.StackFlowModelDao()
    }

    fun getDataFromApi() = ApiModule.newInstance().createApiService().getAllAnswers(1, 20, "stackoverflow")
    fun insertDataToDb(items: StackFlowResponse) = stackFlowModelDao.insertALLAnswers(items)

    fun getDataFromDb(stackOverFlowViewModel: StackOverFlowViewModel): LiveData<PagedList<StackFlowResponse>> {
        val factory = stackFlowModelDao.getAllAnswers()
        Timber.i("factory size $factory.")
        val builder = LivePagedListBuilder(factory, PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .build())
        return builder
                .setBoundaryCallback(StackOverFlowViewModel.StackOverFlowBoundaryCallback(stackOverFlowViewModel))
                .build()

    }
}