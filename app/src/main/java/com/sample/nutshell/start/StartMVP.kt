package com.sample.nutshell.start

import com.sample.nutshell.base.MvpInteractor
import com.sample.nutshell.base.MvpPresenter
import com.sample.nutshell.base.MvpView
import com.sample.nutshell.data.model.User
import com.sample.nutshell.di.PerActivity
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