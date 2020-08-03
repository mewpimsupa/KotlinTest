package pimsupa.test.kotlintest.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_shared_preferences.*
import pimsupa.test.kotlintest.R

/**shared preference แบบinitial ค่าใหม่ทุกรอบที่ oncreate*/
class SharedPreferenceActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        sharedPreferences = application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        button_count.setOnClickListener {
            count += 1
            button_count.text = count.toString()
        }

        button_clear.setOnClickListener {
            count = 0
            sharedPreferences.edit().clear().apply()
        }
    }

    override fun onResume() {
        super.onResume()
        count = sharedPreferences.getString("key", "0")?.toIntOrNull()!!
        button_count.text = count.toString()
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences.edit().putString("key", count.toString()).apply()
    }

}