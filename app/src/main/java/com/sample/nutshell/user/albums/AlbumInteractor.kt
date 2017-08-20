package com.sample.nutshell.user.albums

import com.sample.nutshell.base.BaseInteractor
import com.sample.nutshell.data.db.Storage
import com.sample.nutshell.data.model.Album
import com.sample.nutshell.data.network.ApiServiceHelper
import io.reactivex.Completable
import javax.inject.Inject

class AlbumInteractor @Inject constructor(private val apiServiceHelper: ApiServiceHelper,
                                          private val storage: Storage) :
        BaseInteractor(), AlbumMVP.Interactor {

    private var albums: List<Album> = listOf()
    override val albumCount: Int
        get() = albums.count()

    override fun loadAlbums(userId: Int, skipCache: Boolean): Completable {
        val tmpUsers: List<Album>
        if (!skipCache) {
            tmpUsers = storage.getAlbums(userId)
            if (tmpUsers.isNotEmpty()) {
                albums = tmpUsers
                return Completable.complete()
            }
        }
        return apiServiceHelper.getUserAlbums(userId)
                .doOnSuccess {
                    albums = it
                    storage.putAlbums(albums)
                }
                .toCompletable()
    }


    override fun getAlbum(position: Int): Album {
        return albums[position] //TODO проверку на границы массива
    }
}