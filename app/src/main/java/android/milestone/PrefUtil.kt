package android.milestone

import android.content.Context
import android.content.SharedPreferences

object PrefUtil {
    private const val DEF_PREF_NAME = "Sweep"


    private fun getPref(context: Context, name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun setStringValue(context: Context, name: String, value: String) {
        val pref = getPref(
            context,
            DEF_PREF_NAME
        )
        val editor = pref.edit()
        editor.putString(name, value)
        editor.apply()
    }

    fun getStringValue(context: Context, name: String, defValue: String): String? {
        val pref = getPref(
            context,
            DEF_PREF_NAME
        )
        return pref.getString(name, defValue)
    }
}
