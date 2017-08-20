package com.sample.nutshell.di.component

import android.app.Application
import android.content.Context
import com.sample.nutshell.App
import com.sample.nutshell.data.db.Storage
import com.sample.nutshell.data.network.ApiServiceHelper
import com.sample.nutshell.di.module.ApplicationModule
import com.sample.nutshell.di.module.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ServiceModule::class))
interface ApplicationComponent {

    fun inject(app: App)

    fun context(): Context

    fun application(): Application

    fun apiHelper(): ApiServiceHelper

    fun storage(): Storage

    //Pref helper
    //Api helper
}