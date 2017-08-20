package com.sample.xxx.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sample.xxx.App
import com.sample.xxx.di.component.ActivityComponent
import com.sample.xxx.di.component.DaggerActivityComponent
import com.sample.xxx.di.module.ActivityModule

abstract class BaseActivity : AppCompatActivity(), MvpView {

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .applicationComponent((application as App).applicationComponent)
                .activityModule(ActivityModule())
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}