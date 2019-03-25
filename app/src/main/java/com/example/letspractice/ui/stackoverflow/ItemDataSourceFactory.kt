package com.example.letspractice.ui.stackoverflow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.letspractice.entity.Items

class ItemDataSourceFactory : DataSource.Factory<Int, Items>() {
    val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Items>>()

    override fun create(): DataSource<Int, Items> {
        val itemDataSource = ItemDataSource()
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getLiveDataSource() = itemLiveDataSource

}