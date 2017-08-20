package com.sample.nutshell.data.network

import com.sample.nutshell.data.model.Album
import com.sample.nutshell.data.model.Photo
import com.sample.nutshell.data.model.Post
import com.sample.nutshell.data.model.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiServiceHelper
@Inject
constructor(private val apiService: ApiService) {
    fun getUsers(): Single<List<User>> {
        return apiService.getUsers()
                .map {
                    it.map {
                        User(it)
                    }
                }
    }
    fun getUserAlbums(userId: Int): Single<List<Album>> {
        return apiService.getUserAlbums(userId)
    }

    fun getUserPosts(userId: Int): Single<List<Post>> {
        return apiService.getUserPosts(userId)
    }

    fun getAlbumPhotos(albumId: Int): Single<List<Photo>> {
        return apiService.getAlbumPhotos(albumId)
    }

    fun savePost(post: Post): Completable {
        return apiService.savePost(post)
    }


}