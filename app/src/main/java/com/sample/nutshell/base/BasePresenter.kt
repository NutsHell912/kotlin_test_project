package com.sample.nutshell.base

import javax.inject.Inject

open class BasePresenter<V : MvpView, I: MvpInteractor>
@Inject constructor(private val mvpInteractor: I) : MvpPresenter<V, I> {

    private var mvpView: V? = null

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        mvpView = null
    }

    override fun getMvpView(): V? {
        return mvpView
    }

    override fun getInteractor(): I {
        return mvpInteractor
    }

}