package pimsupa.test.kotlintest.utils.dagger

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

class MySharedPreferences (private val mSharedPreferences: SharedPreferences) {

    fun putData(key: String, data: String) {
        mSharedPreferences.edit().putString(key, data).apply()
    }

    fun getData(key: String): String {
        return mSharedPreferences.getString(key, "")!!
    }

    fun clearData(key: String) {
        mSharedPreferences.edit().putString(key, "").apply()
    }

    fun clearSharedPreference() {
        mSharedPreferences.edit().clear().apply()
    }

}
