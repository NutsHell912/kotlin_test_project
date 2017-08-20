package com.sample.xxx.base

import android.support.v4.app.Fragment
import com.sample.xxx.App
import com.sample.xxx.di.component.ActivityComponent
import com.sample.xxx.di.component.DaggerActivityComponent
import com.sample.xxx.di.module.ActivityModule

abstract class BaseFragment: Fragment(), MvpView {
    val fragmentComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .applicationComponent((context.applicationContext as App).applicationComponent)
                .activityModule(ActivityModule())
                .build()
    }

}