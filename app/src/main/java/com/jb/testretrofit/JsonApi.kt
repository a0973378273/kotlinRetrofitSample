package com.jb.testretrofit

import retrofit2.Call
import retrofit2.http.*

interface JsonApi {
    //https://jsonplaceholder.typicode.com/albums/1
    @GET("albums/1")
    fun getAlbums(): Call<AlbumsData>

    //https://jsonplaceholder.typicode.com/albums/{id}
    @GET("albums/{id}")
    fun getAlbumsByIdFromPath(@Path("id") Id:Int): Call<AlbumsData>

    //https://jsonplaceholder.typicode.com/albums?postId={Id}
    @GET("albums")
    fun getAlbumsById(@Query("postId") Id:Int): Call<List<AlbumsData>>

    //post albumsData to https://jsonplaceholder.typicode.com
    @POST("albums")
    fun postAlbums(@Body albumsData: AlbumsData): Call<AlbumsData>
}