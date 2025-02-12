package com.example.baseprojectlib.utils

import android.util.Log
import com.google.gson.Gson

object Logger {
    var isActiveLog = true
    private const val TAG = "[Base_Project]"

    fun d(vararg objects: Any?) {
        if (Constants.DEBUG || isActiveLog)
            Log.d(TAG, getString(*objects))
    }

    fun e(vararg objects: Any?) {
        if (Constants.DEBUG || isActiveLog)
            Log.e(TAG, getString(*objects))
    }

    fun i(s: String?, vararg objects: Any?) {
        if (Constants.DEBUG || isActiveLog)
            Log.i(TAG, getString(*objects))
    }

    fun v(s: String?, vararg objects: Any?) {
        if (Constants.DEBUG || isActiveLog)
            Log.v(TAG, getString(*objects))
    }

    fun j(vararg objects: Any?) {
        if (Constants.DEBUG || isActiveLog)
            Log.d(TAG, getStringJson(*objects))
    }

    private fun getString(vararg objects: Any?): String {
        return objects.foldIndexed("") { index, acc, any ->
            if (index > 0) acc.plus(" ; ").plus(any)
            else any.toString()
        }
    }

    private fun getStringJson(vararg objects: Any?): String {
        val gson = Gson()
        return objects.foldIndexed("") { index, acc, any ->
            if (index > 0) acc.plus(" ; ").plus(gson.toJson(any))
            else gson.toJson(any)
        }
    }
}