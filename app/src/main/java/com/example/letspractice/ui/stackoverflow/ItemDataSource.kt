package com.example.letspractice.ui.stackoverflow

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.letspractice.ApiModule
import com.example.letspractice.entity.Items
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ItemDataSource : PageKeyedDataSource<Int, Items>() {
    val PAGE_KEY: Int = 1
    val PAGE_SIZE: Int = 20
    val SITE_NAME: String = "stackoverflow"

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Items>) {
        Log.i("ItemDataSource", "load initial called")
        // Timber.i("load initial called")
        ApiModule.newInstance().createApiService().getAllAnswers(PAGE_KEY, PAGE_SIZE, SITE_NAME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    Log.i("ItemDataSource", "hasMoreinitial ${it.hasMore}")
                    callback.onResult(it.items, null, PAGE_KEY + 1)
                }
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Items>) {
        Log.i("ItemDataSource", "load before called")
        ApiModule.newInstance().createApiService().getAllAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    val key: Int? = if (params.key > 1)
                        params.key - 1
                    else
                        null
                    callback.onResult(it.items, key)
                }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Items>) {
        Log.i("ItemDataSource", "load after called")
        ApiModule.newInstance().createApiService().getAllAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    Log.i("ItemDataSource", "hasMore ${it.hasMore}")
                    val key: Int? = if (!it.hasMore)
                        params.key + 1
                    else
                        null
                    callback.onResult(it.items, key)
                }
    }
}