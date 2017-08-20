package com.sample.nutshell.data.network

import com.sample.nutshell.data.model.Album
import com.sample.nutshell.data.model.Photo
import com.sample.nutshell.data.model.Post
import io.reactivex.Single
import retrofit2.http.GET
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @GET("/users")
    fun getUsers(): Single<List<UserDto>>

    @GET("/users/{id}/albums")
    fun getUserAlbums(@Path("id") userId: Int): Single<List<Album>>

    @GET("/users/{id}/posts")
    fun getUserPosts(@Path("id") userId: Int): Single<List<Post>>

    @GET("/albums/{id}/photos")
    fun getAlbumPhotos(@Path("id") albumId: Int): Single<List<Photo>>

    @POST("/albums/posts")
    fun savePost(@Body post: Post): Completable

}