package com.jb.testretrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create

class ApiRepository {
    val jsonApi : JsonApi by lazy { RetrofitManager().getRetrofit().create(JsonApi::class.java) }
    //reified

    fun getAllAlbums(): Call<AlbumsData>{
        return jsonApi.getAlbums()
    }

    fun getAlbumsByIdFromPath(id:Int): Call<AlbumsData>{
        return jsonApi.getAlbumsByIdFromPath(id)
    }

    fun getAlbumsById(id:Int): Call<List<AlbumsData>>{
        return jsonApi.getAlbumsById(id)
    }

    fun postAlbums(albumsData:AlbumsData): Call<AlbumsData>{
        return jsonApi.postAlbums(albumsData)
    }
}