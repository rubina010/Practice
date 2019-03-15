package com.example.letspractice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.letspractice.Entity.StackFlowResponse
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    fun initialize() {
       val call=ApiModule.newInstance().createApiService().getAllAnswers(1, 20, "stackoverflow")


    }
}
