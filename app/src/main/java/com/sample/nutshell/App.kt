package com.sample.nutshell

import android.app.Application
import com.sample.nutshell.di.component.ApplicationComponent
import com.sample.nutshell.di.component.DaggerApplicationComponent
import com.sample.nutshell.di.module.ApplicationModule
import com.sample.nutshell.di.module.ServiceModule

class App: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .serviceModule(ServiceModule())
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)
    }

    fun getComponent(): ApplicationComponent {
        return applicationComponent
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}