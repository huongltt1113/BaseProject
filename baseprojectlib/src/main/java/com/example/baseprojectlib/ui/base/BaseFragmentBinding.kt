package com.example.baseprojectlib.ui.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.baseprojectlib.extension.popFragment
import com.example.baseprojectlib.utils.Logger

abstract class BaseFragmentBinding<T : ViewDataBinding> : BaseFragment() {

    open lateinit var dataBinding: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            dataBinding = DataBindingUtil.bind(view)!!
            dataBinding.lifecycleOwner = this
        } catch (e: Exception) {
            Logger.e(e)
            popFragment()
            return
        }
        if (isInitialized) {
            super.onViewCreated(view, savedInstanceState)
        }
    }

    protected val isInitialized get() = this::dataBinding.isInitialized

}