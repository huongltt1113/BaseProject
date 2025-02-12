package com.example.baseprojectlib.ui.base

interface BaseView {
    fun getContentViewId(): Int

    fun initializeViews()

    fun registerListeners()

    fun initializeData()
}