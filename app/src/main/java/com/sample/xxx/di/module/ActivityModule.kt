package com.sample.xxx.di.module

import com.sample.xxx.di.PerActivity
import com.sample.xxx.photo.PhotoInteractor
import com.sample.xxx.photo.PhotoMVP
import com.sample.xxx.photo.PhotoPresenter
import com.sample.xxx.start.*
import com.sample.xxx.user.albums.AlbumInteractor
import com.sample.xxx.user.albums.AlbumMVP
import com.sample.xxx.user.albums.AlbumPresenter
import com.sample.xxx.user.posts.PostsInteractor
import com.sample.xxx.user.posts.PostsMVP
import com.sample.xxx.user.posts.PostsPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {


    @Provides
    @PerActivity
    fun provideStartPresenter(presenter: StartPresenter<StartMVP.View, StartMVP.Interactor>):
            StartMVP.Presenter<StartMVP.View, StartMVP.Interactor> {
        return presenter
    }

    @Provides
    @PerActivity
    fun provideStartMvpInteractor(interactor: StartInteractor):
            StartMVP.Interactor {
        return interactor
    }

    @Provides
    @PerActivity
    fun providePostsPresenter(presenter: PostsPresenter<PostsMVP.View, PostsMVP.Interactor>):
            PostsMVP.Presenter<PostsMVP.View, PostsMVP.Interactor> {
        return presenter
    }

    @Provides
    @PerActivity
    fun providePostsMvpInteractor(interactor: PostsInteractor):
            PostsMVP.Interactor {
        return interactor
    }

    @Provides
    @PerActivity
    fun provideAlbumPresenter(presenter: AlbumPresenter<AlbumMVP.View, AlbumMVP.Interactor>):
            AlbumMVP.Presenter<AlbumMVP.View, AlbumMVP.Interactor> {
        return presenter
    }

    @Provides
    @PerActivity
    fun provideAlbumMvpInteractor(interactor: AlbumInteractor):
            AlbumMVP.Interactor {
        return interactor
    }

    @Provides
    @PerActivity
    fun providePhotoPresenter(presenter: PhotoPresenter<PhotoMVP.View, PhotoMVP.Interactor>):
            PhotoMVP.Presenter<PhotoMVP.View, PhotoMVP.Interactor> {
        return presenter
    }

    @Provides
    @PerActivity
    fun providePhotoMvpInteractor(interactor: PhotoInteractor):
            PhotoMVP.Interactor {
        return interactor
    }


}