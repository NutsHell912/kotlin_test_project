package com.sample.xxx.user.albums

import com.sample.xxx.base.MvpInteractor
import com.sample.xxx.base.MvpPresenter
import com.sample.xxx.base.MvpView
import com.sample.xxx.data.model.Album
import com.sample.xxx.di.PerActivity
import com.sample.xxx.user.albums.AlbumAdapter
import io.reactivex.Completable

interface AlbumMVP {

    interface Interactor : MvpInteractor {
        val albumCount: Int

        fun loadAlbums(userId: Int, skipCache: Boolean = false): Completable
        fun getAlbum(position:Int): Album
    }

    @PerActivity
    interface Presenter<V : AlbumMVP.View, I : AlbumMVP.Interactor>
        : MvpPresenter<V, I> {

        fun onRefresh()
        fun bindItemView(holder: AlbumAdapter.ViewHolder, position: Int)
        fun getUserId(position: Int): Int
        val albumCount: Int
        fun onAlbumClicked(adapterPosition: Int)
    }

    interface View : MvpView {
        val userId: Int
        fun renderList()
        fun showError(error: String)
        fun navigateToPhotoActivity(albumId: Int)

    }
    interface ItemView {
        fun renderAlbumTitle(albumTitle: String)

    }

}