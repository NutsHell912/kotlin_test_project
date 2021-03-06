package com.sample.nutshell.start

import com.sample.nutshell.base.BaseInteractor
import com.sample.nutshell.data.db.Storage
import com.sample.nutshell.data.model.User
import com.sample.nutshell.data.network.ApiServiceHelper
import io.reactivex.Completable
import javax.inject.Inject

class StartInteractor @Inject constructor( private val apiServiceHelper: ApiServiceHelper,
                                           private val storage: Storage) :
        BaseInteractor(), StartMVP.Interactor{

    private var users: List<User> = listOf()
    override val usersCount: Int
        get() = users.count()

    override fun loadUsers(skipCache: Boolean): Completable {
        val tmpUsers: List<User>
        if(!skipCache) {
            tmpUsers = storage.getUsers()
            if(tmpUsers.isNotEmpty()) {
                users = tmpUsers
                return Completable.complete()
            }
        }
        return apiServiceHelper.getUsers()
                .doOnSuccess {
                    users = it
                    storage.putUsers(users)
                }
                .toCompletable()
    }

    override fun getUser(position: Int): User {
        return users[position] //TODO проверку на границы массива
    }
}