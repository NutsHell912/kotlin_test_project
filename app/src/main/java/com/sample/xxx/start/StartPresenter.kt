package com.sample.xxx.start

import com.sample.xxx.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StartPresenter<V : StartMVP.View, I : StartMVP.Interactor>
@Inject constructor(mvpInteractor: I) : BasePresenter<V, I>(mvpInteractor),
        StartMVP.Presenter<V, I> {

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
        if(!userDisposable.isDisposed) {
            userDisposable.dispose()
        }
        userDisposable = getInteractor().loadUsers(skipCache)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getMvpView()?.renderList()
                }, {
                    getMvpView()?.showError(it.message ?: "unknown error")
                })
    }

    override fun onUserClicked(adapterPosition: Int) {
        val user = getInteractor().getUser(adapterPosition)
        getMvpView()?.navigateToUserActivity(user.id)
    }

    override fun bindItemView(holder: UserAdapter.ViewHolder, position: Int) {
        val user = getInteractor().getUser(position)
        holder.renderUserName(userName = user.name)
        holder.renderUserEmail(userEmail = user.email)
        holder.renderUserAddress(userAddress = user.address)
    }

    override fun getUserId(position: Int): Int {
        return getInteractor().getUser(position).id
    }

    override val usersCount: Int
        get() = getInteractor().usersCount
}