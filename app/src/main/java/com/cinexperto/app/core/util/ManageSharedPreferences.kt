package com.cinexperto.app.core.util

import com.cinexperto.app.R
import android.content.Context

object ManageSharedPreferences {

    fun savePreferences(key:String, value:String, context:Context){
        val prefs = context.getSharedPreferences(context.getString(R.string.preferences_key), Context.MODE_PRIVATE)
        prefs.edit().putString(key, value).apply()
    }

    fun getPreferences(key: String, context: Context):String {
        val prefs = context.getSharedPreferences(context.getString(R.string.preferences_key), Context.MODE_PRIVATE)
        return prefs.getString(key, "") ?: ""
    }


}