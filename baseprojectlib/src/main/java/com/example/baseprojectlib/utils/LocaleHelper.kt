package com.example.baseprojectlib.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.example.baseprojectlib.local.LocalData
import java.util.Locale

class LocaleHelper {
    fun updateResources(context: Context): Context{
        val resources = context.resources
        val config = Configuration(resources.configuration)
        val localeCode = LocalData(context, "sharedPreferences").langCode
        val locale = Locale(localeCode)

        config.setLocale(locale)
        val newContext = context.createConfigurationContext(config)

        return newContext
    }
}