package com.sample.nutshell.base

import android.support.v4.app.Fragment
import com.sample.nutshell.App
import com.sample.nutshell.di.component.ActivityComponent
import com.sample.nutshell.di.component.DaggerActivityComponent
import com.sample.nutshell.di.module.ActivityModule

abstract class BaseFragment: Fragment(), MvpView {
    val fragmentComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .applicationComponent((context.applicationContext as App).applicationComponent)
                .activityModule(ActivityModule())
                .build()
    }

}