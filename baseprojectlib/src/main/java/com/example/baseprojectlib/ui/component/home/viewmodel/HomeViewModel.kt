package com.example.baseprojectlib.ui.component.home.viewmodel

import com.example.baseprojectlib.repository.ScriptRepository
import com.example.baseprojectlib.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val scriptRepository: ScriptRepository) : BaseViewModel() {

    companion object {
        const val TAG = "HomeViewModel"
    }
}