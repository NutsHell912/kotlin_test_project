package com.sample.nutshell.base

interface MvpPresenter<V: MvpView, I: MvpInteractor> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun getMvpView(): V?

    fun getInteractor(): I
}