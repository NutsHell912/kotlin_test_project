package com.sample.nutshell.user.posts

import com.sample.nutshell.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsPresenter<V : PostsMVP.View, I : PostsMVP.Interactor>
@Inject constructor(mvpInteractor: I) : BasePresenter<V, I>(mvpInteractor),
        PostsMVP.Presenter<V, I> {

    private var userDisposable = Disposables.disposed()

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        loadUsers()
    }

    override fun onDetach() {
        userDisposable.dispose()
        super.onDetach()
    }
    override fun onRefresh() {
        loadUsers(true)
    }

    private fun loadUsers(skipCache: Boolean = false) {
        getMvpView()?.let {
            if (!userDisposable.isDisposed) {
                userDisposable.dispose()
            }
            userDisposable = getInteractor().loadPosts(it.userId, skipCache)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        getMvpView()?.renderList()
                    }, {
                        getMvpView()?.showError(it.message ?: "unknown error")
                    })
        }
    }


    override fun onPostCreating(title: String, body: String) {
        getMvpView()?.let {
            getInteractor().onPostCreating(it.userId, title, body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        getMvpView()?.saveSuccess()
                    }, {
                        getMvpView()?.showError(it.message ?: "unknown error")
                    })
        }
    }

    override fun bindItemView(holder: PostsAdapter.ViewHolder, position: Int) {
        val post = getInteractor().getPost(position)
        holder.renderPostTitle(postTitle = post.title)
        holder.renderPostBody(postBody = post.body)
    }

    override fun getUserId(position: Int): Int {
        return getInteractor().getPost(position).hashCode()
    }

    override val postsCount: Int
        get() = getInteractor().postsCount
}