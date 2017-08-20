package com.sample.xxx.di.component


import com.sample.xxx.di.PerActivity
import com.sample.xxx.di.module.ActivityModule
import com.sample.xxx.photo.PhotoActivity
import com.sample.xxx.start.StartActivity
import com.sample.xxx.user.albums.AlbumFragment
import com.sample.xxx.user.posts.PostsFragment
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: StartActivity)
    fun inject(activity: PhotoActivity)

    fun inject(fragment: PostsFragment)
    fun inject(fragment: AlbumFragment)

}