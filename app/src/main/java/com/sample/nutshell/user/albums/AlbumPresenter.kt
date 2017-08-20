package com.sample.nutshell.user.albums

import com.sample.nutshell.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumPresenter<V : AlbumMVP.View, I : AlbumMVP.Interactor>
@Inject constructor(mvpInteractor: I) : BasePresenter<V, I>(mvpInteractor),
        AlbumMVP.Presenter<V, I> {

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
            userDisposable = getInteractor().loadAlbums(it.userId, skipCache)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        getMvpView()?.renderList()
                    }, {
                        getMvpView()?.showError(it.message ?: "unknown error")
                    })
        }
    }

    override fun onAlbumClicked(adapterPosition: Int) {
        getMvpView()?.navigateToPhotoActivity(getInteractor().getAlbum(adapterPosition).id)
    }

    override fun bindItemView(holder: AlbumAdapter.ViewHolder, position: Int) {
        val album = getInteractor().getAlbum(position)
        holder.renderAlbumTitle(albumTitle = album.title)
    }

    override fun getUserId(position: Int): Int {
        return getInteractor().getAlbum(position).hashCode()
    }

    override val albumCount: Int
        get() = getInteractor().albumCount
}