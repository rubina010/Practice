package com.example.letspractice.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.letspractice.Article
import com.example.letspractice.utils.NetworkState

class FeedDataSource : PageKeyedDataSource<Long, Article>() {
    //networkState and initLoading are variables used to update the UI when the data is fetched by displaying the progress bar
    private val networkState = MutableLiveData<NetworkState>()
    private val initLoading = MutableLiveData<NetworkState>()


    // this method is responsible for the initial loading of the data when the app screen is launched for the first time
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Article>) {
        networkState.postValue(NetworkState.LOADING)
        initLoading.postValue(NetworkState.LOADING)

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Article>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Article>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}