package com.sample.nutshell.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val application: Application) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideApplication(): Application = application

    //Provide api helper

    //Provide pref helper

    //Provide api key etc.

}