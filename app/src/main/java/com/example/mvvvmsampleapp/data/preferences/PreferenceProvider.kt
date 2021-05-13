package com.example.mvvvmsampleapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(
    context: Context

) {

    private val appContext = context.applicationContext

    private val prefernces: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSavedAt(savedAt: String) {
        prefernces.edit().putString(

            KEY_SAVED_AT,
            savedAt

        ).apply()
    }

    fun getlastSavedAt(): String? {

        return prefernces.getString(KEY_SAVED_AT, null)
    }


}