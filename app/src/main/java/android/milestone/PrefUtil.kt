package android.milestone

import android.content.Context
import androidx.core.content.edit

object PrefUtil {
    private const val DEF_PREF_NAME = "Sweep"

    private val prefUtil =
        App.instance.applicationContext.getSharedPreferences(DEF_PREF_NAME, Context.MODE_PRIVATE)

    fun setStringValue(name: String, value: String) {
        prefUtil.edit {
            putString(name, value)
            apply()
        }
    }

    fun getStringValue(name: String, defValue: String): String =
        prefUtil.getString(name, defValue) ?: ""

}
