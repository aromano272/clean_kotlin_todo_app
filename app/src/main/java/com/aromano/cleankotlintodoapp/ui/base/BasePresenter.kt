package com.aromano.cleankotlintodoapp.ui.base

import java.lang.ref.WeakReference


abstract class BasePresenter<T> {

    private var view: WeakReference<T>? = null

    constructor(view: T) {
        this.view = WeakReference<T>(view)
    }

    protected fun getView(): T? {
        return view?.get()
    }

    protected fun attach(view: T) {
        this.view = WeakReference(view)
    }

    protected fun detach() {
        this.view = null
    }

}