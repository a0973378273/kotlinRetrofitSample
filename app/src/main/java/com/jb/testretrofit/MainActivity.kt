package com.jb.testretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(ApiViewModel::class.java)
        viewModel.getAlbumAsync()
        viewModel.albumsLiveDataList.observe(this, Observer { Log.d("test","List:"+it.toString()) })
        viewModel.albumsLiveData1.observe(this, Observer { Log.d("test","1:"+it.toString()) })
        viewModel.albumsLiveData2.observe(this, Observer { Log.d("test","2:"+it.toString()) })
        viewModel.albumsLiveData3.observe(this, Observer { Log.d("test","3:"+it.toString()) })
    }
}
