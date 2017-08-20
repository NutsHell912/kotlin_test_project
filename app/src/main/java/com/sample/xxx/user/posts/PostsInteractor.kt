package com.sample.xxx.user.posts

import com.sample.xxx.base.BaseInteractor
import com.sample.xxx.data.db.Storage
import com.sample.xxx.data.model.Post
import com.sample.xxx.data.network.ApiServiceHelper
import io.reactivex.Completable
import javax.inject.Inject

class PostsInteractor @Inject constructor( private val apiServiceHelper: ApiServiceHelper,
                                           private val storage: Storage) :
        BaseInteractor(), PostsMVP.Interactor{

    private var posts: List<Post> = listOf()
    override val postsCount: Int
        get() = posts.count()

    override fun loadPosts(userId: Int, skipCache: Boolean): Completable {
        val tmpUsers: List<Post>
        if(!skipCache) {
            tmpUsers = storage.getPosts(userId)
            if(tmpUsers.isNotEmpty()) {
                posts = tmpUsers
                return Completable.complete()
            }
        }
        return apiServiceHelper.getUserPosts(userId)
                .doOnSuccess {
                    posts = it
                    storage.putPosts(posts)
                }
                .toCompletable()
    }

    override fun onPostCreating(userId: Int, title: String, body: String): Completable {
        return apiServiceHelper.savePost(Post(userId, title, body))
    }

    override fun getPost(position: Int): Post {
        return posts[position] //TODO проверку на границы массива
    }
}