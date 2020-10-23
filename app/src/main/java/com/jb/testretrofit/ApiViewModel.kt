package com.jb.testretrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiViewModel : ViewModel() {

    private val albumsDataList = MutableLiveData<List<AlbumsData>>()
    val albumsLiveDataList: LiveData<List<AlbumsData>> = albumsDataList
    private val albumsData1 = MutableLiveData<AlbumsData>()
    val albumsLiveData1: LiveData<AlbumsData> = albumsData1
    private val albumsData2 = MutableLiveData<AlbumsData>()
    val albumsLiveData2: LiveData<AlbumsData> = albumsData2
    private val albumsData3 = MutableLiveData<AlbumsData>()
    val albumsLiveData3: LiveData<AlbumsData> = albumsData3

    val apiRepository: ApiRepository by lazy { ApiRepository() }

    //同步執行
    fun getAlbumSync() {
        Thread(Runnable {
            var apiResponse1 = apiRepository.getAlbumsById(1).execute()
            var apiResponse2 = apiRepository.getAlbumsByIdFromPath(2).execute()
            var apiResponse3 = apiRepository.getAllAlbums().execute()
            var apiResponse4 = apiRepository.postAlbums(AlbumsData(1, 1, "Castle on the Hill")).execute()

            if (isSuccess(apiResponse1)) {
                albumsDataList.postValue(apiResponse1.body())
            }
            if (isSuccess(apiResponse2)) {
                albumsData1.postValue(apiResponse2.body())
            }
            if (isSuccess(apiResponse3)) {
                albumsData2.postValue(apiResponse3.body())
            }
            if (isSuccess(apiResponse4)) {
                albumsData3.postValue(apiResponse4.body())
            }

        }).start()
    }

    //非同步執行
    fun getAlbumAsync() {

        apiRepository.getAlbumsById(1).enqueue(object : Callback<List<AlbumsData>> {
            override fun onFailure(call: Call<List<AlbumsData>>, t: Throwable) {
                Log.d("test","Fail1")
            }
            override fun onResponse(call: Call<List<AlbumsData>>,response: Response<List<AlbumsData>>) {
                albumsDataList.value = response.body()
            }
        })

        apiRepository.getAlbumsByIdFromPath(2).enqueue(object : Callback<AlbumsData>{
            override fun onFailure(call: Call<AlbumsData>, t: Throwable) {
                Log.d("test","Fail2")
            }
            override fun onResponse(call: Call<AlbumsData>, response: Response<AlbumsData>) {
                albumsData1.value =response.body()
            }
        })

        apiRepository.getAllAlbums().enqueue(object : Callback<AlbumsData>{
            override fun onFailure(call: Call<AlbumsData>, t: Throwable) {
                Log.d("test","Fail3")
            }
            override fun onResponse(call: Call<AlbumsData>, response: Response<AlbumsData>) {
                albumsData2.value=response.body()
            }
        })

        apiRepository.postAlbums(AlbumsData(1, 1, "Castle on the Hill")).enqueue(object : Callback<AlbumsData>{
            override fun onFailure(call: Call<AlbumsData>, t: Throwable) {
                Log.d("test","Fail4")
            }
            override fun onResponse(call: Call<AlbumsData>, response: Response<AlbumsData>) {
                albumsData3.value=response.body()
            }
        })
    }

    private fun <T> isSuccess(apiResponse: Response<T>): Boolean {
        return apiResponse.isSuccessful and (apiResponse.code() == 200)
    }
}


