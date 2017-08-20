package com.sample.nutshell.di.module

import com.sample.nutshell.di.PerActivity
import com.sample.nutshell.photo.PhotoInteractor
import com.sample.nutshell.photo.PhotoMVP
import com.sample.nutshell.photo.PhotoPresenter
import com.sample.nutshell.start.*
import com.sample.nutshell.user.albums.AlbumInteractor
import com.sample.nutshell.user.albums.AlbumMVP
import com.sample.nutshell.user.albums.AlbumPresenter
import com.sample.nutshell.user.posts.PostsInteractor
import com.sample.nutshell.user.posts.PostsMVP
import com.sample.nutshell.user.posts.PostsPresenter
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