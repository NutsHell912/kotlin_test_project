package com.sample.nutshell.user.posts

import com.sample.nutshell.base.MvpInteractor
import com.sample.nutshell.base.MvpPresenter
import com.sample.nutshell.base.MvpView
import com.sample.nutshell.data.model.Post
import com.sample.nutshell.di.PerActivity
import io.reactivex.Completable

interface PostsMVP {

    interface Interactor : MvpInteractor {
        val postsCount: Int

        fun loadPosts(userId: Int,skipCache: Boolean = false): Completable
        fun getPost(position:Int): Post
        fun onPostCreating(userId: Int,title: String, body: String): Completable
    }

    @PerActivity
    interface Presenter<V : View, I : Interactor>
        : MvpPresenter<V, I> {

        fun onRefresh()
        fun bindItemView(holder: PostsAdapter.ViewHolder, position: Int)
        fun getUserId(position: Int): Int
        val postsCount: Int
        fun onPostCreating(title: String, body: String)
    }

    interface View : MvpView {
        val userId: Int
        fun renderList()
        fun showError(error: String)
        fun saveSuccess()
    }
    interface ItemView {
        fun renderPostTitle(postTitle: String)
        fun renderPostBody(postBody: String)

    }

}