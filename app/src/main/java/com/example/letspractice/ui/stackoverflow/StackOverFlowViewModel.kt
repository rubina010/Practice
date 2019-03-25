package com.example.letspractice.ui.stackoverflow


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.letspractice.entity.Items
import com.example.letspractice.entity.StackFlowResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import timber.log.Timber

class StackOverFlowViewModel(val app: Application) : AndroidViewModel(app) {
    var repository = StackOverFlowRepository(app)
    lateinit var itemPageList: LiveData<PagedList<Items>>
    var livePageList: LiveData<PagedList<StackFlowResponse>>
    lateinit var liveDataSource: LiveData<PageKeyedDataSource<Int, Items>>

    fun getFactorySource() {
        val itemDataSourceFactory = ItemDataSourceFactory()
        liveDataSource = itemDataSourceFactory.itemLiveDataSource
        val config: PagedList.Config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build()
        itemPageList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

    init {
        livePageList = repository.getDataFromDb(this@StackOverFlowViewModel)
    }

    fun getDataFromApi() {
        doAsync {
            repository.getDataFromApi()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(onNext = {
                        repository.insertDataToDb(it)
                        Timber.i("Data from api")
                    },
                            onError = {
                                Timber.i("${it.printStackTrace()}")
                            })
        }

    }

    /* fun getDataFromDd() = doAsync {
         itemPageList = repository.getDataFromDb(this@StackOverFlowViewModel) as MutableLiveData<PagedList<StackFlowResponse>>
         Timber.i("itemPageList $itemPageList")
     }*/

    fun getPagedList() = livePageList

    class StackOverFlowBoundaryCallback(val stackOverFlowViewModel: StackOverFlowViewModel) :
            PagedList.BoundaryCallback<StackFlowResponse>() {
        override fun onZeroItemsLoaded() {
            stackOverFlowViewModel.getDataFromApi()
        }

        override fun onItemAtEndLoaded(itemAtEnd: StackFlowResponse) {
            stackOverFlowViewModel.getDataFromApi()
        }
    }

}