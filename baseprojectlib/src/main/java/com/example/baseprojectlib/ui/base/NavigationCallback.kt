package com.example.baseprojectlib.ui.base

import androidx.fragment.app.Fragment

interface NavigationCallback {

    fun prepareToPushFragment()
    fun didPushFragment(fragment: Fragment)
    fun didRemoveFragment(fragment: Fragment)
}