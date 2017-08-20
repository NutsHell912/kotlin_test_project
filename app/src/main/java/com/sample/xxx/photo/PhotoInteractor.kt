package com.sample.xxx.photo

import com.sample.xxx.base.BaseInteractor
import com.sample.xxx.data.db.Storage
import com.sample.xxx.data.model.Photo
import com.sample.xxx.data.network.ApiServiceHelper
import io.reactivex.Completable
import javax.inject.Inject

class PhotoInteractor @Inject constructor( private val apiServiceHelper: ApiServiceHelper,
                                           private val storage: Storage) :
        BaseInteractor(), PhotoMVP.Interactor {

    private var photos: List<Photo> = listOf()
    override val photoCount: Int
        get() = photos.count()

    override fun loadPhoto(albumId: Int, skipCache: Boolean): Completable {
        val tmpPhoto: List<Photo>
        if(!skipCache) {
            tmpPhoto = storage.getPhotos(albumId)
            if(tmpPhoto.isNotEmpty()) {
                photos = tmpPhoto
                return Completable.complete()
            }
        }
        return apiServiceHelper.getAlbumPhotos(albumId)
                .doOnSuccess {
                    photos = it
                    storage.putPhotos(photos)
                }
                .toCompletable()
    }

    override fun getPhoto(position: Int): Photo {
        return photos[position] //TODO проверку на границы массива
    }
}