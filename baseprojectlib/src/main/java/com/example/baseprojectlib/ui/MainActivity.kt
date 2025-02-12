package com.example.baseprojectlib.ui

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.provider.Settings
import android.view.View
import io.github.huongltt1113.R
import com.example.baseprojectlib.ui.base.BaseActivityBinding
import com.example.baseprojectlib.ui.component.bottomsheet.RequestInternetBottomSheet
import com.example.baseprojectlib.ui.component.splash.view.AskLanguageFragment
import com.example.baseprojectlib.ui.component.splash.view.IntroFragment
import com.example.baseprojectlib.utils.AppConfig
import com.example.baseprojectlib.utils.Constants
import com.example.baseprojectlib.utils.Logger
import com.example.baseprojectlib.utils.NetworkUtils
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import io.github.huongltt1113.databinding.ActivityMainBinding
import java.util.Locale

@AndroidEntryPoint
class MainActivity : BaseActivityBinding<ActivityMainBinding, MainViewModel>()  {

    private var requestInternetBottomSheet : RequestInternetBottomSheet = RequestInternetBottomSheet()
    override fun getContentViewId() = R.layout.activity_main

    override fun initializeViews() {
        Logger.d("initialize MainActivity")
    }

    override fun registerListeners() {

    }

    override fun initializeData() {

    }

    fun checkShowRequestInternet() {
        try {
            val fragment = this.supportFragmentManager.findFragmentById(R.id.mainNavHostFragment)?.childFragmentManager?.fragments?.get(0)
            if (!NetworkUtils.isNetworkConnected()) {
                dataBinding.llInternetConnect.visibility = View.GONE
                dataBinding.llInternetDisconnect.visibility = View.VISIBLE
                if (!requestInternetBottomSheet.isVisible && (fragment is IntroFragment || fragment is AskLanguageFragment)) {
                    initRequestInternetBottomSheet()
                }
            } else {
                dataBinding.llInternetConnect.visibility = View.GONE
                dataBinding.llInternetDisconnect.visibility = View.GONE
            }
        } catch (ex: Exception) {
            Logger.e(ex.message)
            Firebase.crashlytics.recordException(ex)
        }
    }
    private fun initRequestInternetBottomSheet() {
        requestInternetBottomSheet = RequestInternetBottomSheet()
        requestInternetBottomSheet.updateLanguage(this, localStorage.langCode)
        requestInternetBottomSheet.clickConfirmYes = {
            try {
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                AppConfig.logEventTracking(Constants.MAIN_REQUEST_INTERNET_YES)
            } catch (e: Exception) {
                Logger.e(e.message)
                Firebase.crashlytics.recordException(e)
            }
        }
        requestInternetBottomSheet.clickConfirmNo = {
            try {
                AppConfig.logEventTracking(Constants.MAIN_REQUEST_INTERNET_NO)
            } catch (e: Exception) {
                Logger.e(e.message)
                Firebase.crashlytics.recordException(e)
            }
        }
        requestInternetBottomSheet.clickConfirmCancel = {
            try {
                AppConfig.logEventTracking(Constants.MAIN_REQUEST_INTERNET_CANCEL)
            } catch (e: Exception) {
                Logger.e(e.message)
                Firebase.crashlytics.recordException(e)
            }
        }
        if (this.isFinishing == false) {
            if (requestInternetBottomSheet.isVisible) {
                requestInternetBottomSheet.dismiss()
            }
            this.supportFragmentManager.let {
                requestInternetBottomSheet.show(
                    it,
                    RequestInternetBottomSheet.TAG
                )
            }
        }
    }

    override fun onResume() {
        if (localStorage.langCode.isNotBlank()) {
            val resources: Resources = resources
            val locale = Locale(localStorage.langCode)
            Locale.setDefault(locale)
            val config = Configuration()
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
        }
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        localStorage.lastTimeExitApp = System.currentTimeMillis()
    }

}
