package com.sample.xxx.start

import android.provider.ContactsContract
import com.sample.xxx.base.MvpInteractor
import com.sample.xxx.base.MvpPresenter
import com.sample.xxx.base.MvpView
import com.sample.xxx.data.model.User
import com.sample.xxx.di.PerActivity
import io.reactivex.Completable

interface StartMVP {

    interface Interactor : MvpInteractor {
        val usersCount: Int

        fun loadUsers(skipCache: Boolean = false): Completable
        fun getUser(position:Int): User
    }

    @PerActivity
    interface Presenter<V : StartMVP.View, I : StartMVP.Interactor>
        : MvpPresenter<V, I> {

        fun onRefresh()
        fun onUserClicked(adapterPosition: Int)
        fun bindItemView(holder: UserAdapter.ViewHolder, position: Int)
        fun getUserId(position: Int): Int
        val usersCount: Int
    }

    interface View : MvpView {
        fun renderList()
        fun showError(error: String)
        fun navigateToUserActivity(userId: Int)
    }
    interface ItemView {
        fun renderUserName(userName: String)
        fun renderUserEmail(userEmail: String)
        fun renderUserAddress(userAddress: String)
    }

}