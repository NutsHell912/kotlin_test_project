package com.sample.xxx.di.component

import android.app.Application
import android.content.Context
import com.sample.xxx.App
import com.sample.xxx.data.db.Storage
import com.sample.xxx.data.network.ApiServiceHelper
import com.sample.xxx.di.ApplicationContext
import com.sample.xxx.di.module.ApplicationModule
import com.sample.xxx.di.module.ServiceModule
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