package com.sample.nutshell.photo

import com.sample.nutshell.base.MvpInteractor
import com.sample.nutshell.base.MvpPresenter
import com.sample.nutshell.base.MvpView
import com.sample.nutshell.data.model.Photo
import com.sample.nutshell.di.PerActivity
import io.reactivex.Completable

interface PhotoMVP {

    interface Interactor : MvpInteractor {
        val photoCount: Int

        fun loadPhoto(albumId:Int,skipCache: Boolean = false): Completable
        fun getPhoto(position:Int): Photo
    }

    @PerActivity
    interface Presenter<V : View, I : Interactor>
        : MvpPresenter<V, I> {

        fun onRefresh()
        fun bindItemView(holder: PhotoAdapter.ViewHolder, position: Int)
        fun getUserId(position: Int): Int
        val photoCount: Int
    }

    interface View : MvpView {
        val albumId: Int
        fun renderList()
        fun showError(error: String)
    }
    interface ItemView {
        fun renderPhotoTitle(photoTitle: String)
        fun renderPhoto(photoUrl: String)

    }

}