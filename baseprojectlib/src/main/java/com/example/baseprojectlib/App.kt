package com.example.baseprojectlib

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.MutableLiveData
import com.example.baseprojectlib.local.LocalStorage
import com.example.baseprojectlib.local.MobileIdInfo
import com.example.baseprojectlib.server.Network
import com.example.baseprojectlib.utils.AppConfig
import com.example.baseprojectlib.utils.LocaleHelper
import com.example.baseprojectlib.ads.GoogleMobileAdsConsentManager
import com.example.baseprojectlib.remote.RemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class App : Application() {
    @Inject
    lateinit var googleMobileAdsConsentManager: GoogleMobileAdsConsentManager
    @Inject
    lateinit var localStorage: LocalStorage

    @Inject
    lateinit var network: Network

    @Inject
    @MobileIdInfo
    lateinit var androidId: String
    var isInitMobileAdsComplete = false
    var onHasConfig = MutableLiveData<Boolean>()

    @SuppressLint("HardwareIds")
    override fun onCreate() {
        super.onCreate()
        instance = this

        CoroutineScope(Dispatchers.IO).launch {
            AppConfig.setup(applicationContext)
            RemoteConfig.ANDROID_ID = androidId

            FirebaseRemoteConfig.getInstance().setConfigSettingsAsync(
                FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(if (BuildConfig.DEBUG) 60L else 3600L)
                    .build()
            )
        }
    }

    companion object {
        lateinit var instance: App
    }

    private fun setupRemoteConfig() {
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper().updateResources(base!!))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelper().updateResources(this)
    }
}