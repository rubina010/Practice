package com.example.letspractice.ui.stackoverflow


import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.letspractice.entity.Items
import com.example.letspractice.entity.StackFlowResponse
import io.reactivex.Flowable
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
        livePageList = repository.getDataFromDb()
    }

    fun getDataFromApi() {
        doAsync {
            repository.getDataFromApi(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(onNext = {
                        Toast.makeText(app.baseContext, "datasize ${it.items.size}", Toast.LENGTH_LONG).show()
                        Log.i("StackOverFlowViewModel", "Data from api ${it.items.size}")
                        repository.insertDataToDb(it)

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

    class StackOverFlowBoundaryCallback(val repository: StackOverFlowRepository) :
            PagedList.BoundaryCallback<StackFlowResponse>() {
        private var currentPage: Int = 1

        init {
          //  handleNetworkCall()
        }

        override fun onZeroItemsLoaded() {
            //stackOverFlowViewModel.getDataFromApi()
        }


        override fun onItemAtEndLoaded(itemAtEnd: StackFlowResponse) {
            currentPage++
            handleNetworkCall()

        }

        fun handleNetworkCall() {
            Log.i("StackOverFlowViewModel", "current page $currentPage")
            val listOfData = repository.getDataFromApi(currentPage)
            saveDataInDB(listOfData)
            currentPage = (repository.getAnswerCount() / 20) + 1
        }

        fun saveDataInDB(listOfData: Flowable<StackFlowResponse>) {
            listOfData.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy {
                        repository.insertDataToDb(it)
                    }
        }
    }

    fun clearDatabase() {
        repository.delete()
    }


}