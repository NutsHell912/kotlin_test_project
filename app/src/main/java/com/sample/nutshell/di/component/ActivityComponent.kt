package com.sample.nutshell.di.component


import com.sample.nutshell.di.PerActivity
import com.sample.nutshell.di.module.ActivityModule
import com.sample.nutshell.photo.PhotoActivity
import com.sample.nutshell.start.StartActivity
import com.sample.nutshell.user.albums.AlbumFragment
import com.sample.nutshell.user.posts.PostsFragment
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: StartActivity)
    fun inject(activity: PhotoActivity)

    fun inject(fragment: PostsFragment)
    fun inject(fragment: AlbumFragment)

}