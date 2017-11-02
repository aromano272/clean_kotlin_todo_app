package com.aromano.cleankotlintodoapp.ui.base

import java.lang.ref.WeakReference


abstract class BasePresenter<T>(view: T) {

    private var view: WeakReference<T>? = null

    init {
        this.view = WeakReference(view)
    }

    protected fun getView(): T? = view?.get()

    internal fun attach(view: T) {
        this.view = WeakReference(view)
    }

    internal fun detach() {
        this.view = null
    }

}