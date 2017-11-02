package com.aromano.cleankotlintodoapp.ui.base

import android.support.v7.app.AppCompatActivity


abstract class BaseMVPActivity<T> : AppCompatActivity() {

    private var isLifeCycleManagementEnabled = false

    private var presenter: BasePresenter<T>? = null

    protected fun enableLifeCycleManagement(presenter: BasePresenter<T>) {
        isLifeCycleManagementEnabled = true
        this.presenter = presenter
    }

    override fun onPause() {
        super.onPause()
        if (isLifeCycleManagementEnabled && presenter != null) {
            presenter?.detach()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isLifeCycleManagementEnabled && presenter != null) {
            presenter?.attach(this as T)
        }
    }
}