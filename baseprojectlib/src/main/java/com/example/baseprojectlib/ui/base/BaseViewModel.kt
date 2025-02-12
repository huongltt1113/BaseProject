package com.example.baseprojectlib.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseprojectlib.local.LocalStorage
import com.example.baseprojectlib.repository.FileHelper
import com.example.baseprojectlib.server.ApiClient
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    @Inject
    lateinit var apiClient: ApiClient

    @Inject
    lateinit var localStorage: LocalStorage

    @Inject
    lateinit var fileHelper: FileHelper

    open val composite = CompositeDisposable()
    private val _toastLiveData = MutableLiveData<Any>()
    val toastLiveData get() = _toastLiveData

    override fun onCleared() {
        composite.dispose()
        super.onCleared()
    }

    fun showToast(any: Any) {
        _toastLiveData.postValue(any)
    }

}