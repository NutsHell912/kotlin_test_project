package com.sample.xxx.photo

import com.sample.xxx.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotoPresenter<V : PhotoMVP.View, I : PhotoMVP.Interactor>
@Inject constructor(mvpInteractor: I) : BasePresenter<V, I>(mvpInteractor),
        PhotoMVP.Presenter<V, I> {

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
            userDisposable = getInteractor().loadPhoto(it.albumId, skipCache)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        getMvpView()?.renderList()
                    }, {
                        getMvpView()?.showError(it.message ?: "unknown error")
                    })
        }
    }


    override fun bindItemView(holder: PhotoAdapter.ViewHolder, position: Int) {
        val photo = getInteractor().getPhoto(position)
        holder.renderPhotoTitle(photoTitle = photo.title)
        holder.renderPhoto(photoUrl = photo.url)
    }

    override fun getUserId(position: Int): Int {
        return getInteractor().getPhoto(position).hashCode()
    }

    override val photoCount: Int
        get() = getInteractor().photoCount
}