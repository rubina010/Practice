package com.example.letspractice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letspractice.ui.stackoverflow.ItemAdapter
import com.example.letspractice.ui.stackoverflow.StackOverFlowViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var itemViewModel: StackOverFlowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_main_recycler_view.layoutManager = LinearLayoutManager(this)
        itemViewModel = ViewModelProviders.of(this).get(StackOverFlowViewModel::class.java)
         itemViewModel.getDataFromApi()
        // itemViewModel.getFactorySource()
        val itemAdapter = ItemAdapter(baseContext)
        /* itemViewModel.getPagedList().observe(this, Observer {
             if (it != null)
                 itemAdapter.submitList(it)
         })*/
        itemViewModel.livePageList.observe(this, Observer {
            if (it != null)
                Toast.makeText(baseContext, "pagelist ${it.size}", Toast.LENGTH_LONG).show()
            itemAdapter.submitList(it)
        })
        activity_main_recycler_view.adapter = itemAdapter


    }

    fun initialize() {

    }

}
