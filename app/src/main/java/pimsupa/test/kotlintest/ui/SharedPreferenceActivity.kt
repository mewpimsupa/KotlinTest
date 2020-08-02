package pimsupa.test.kotlintest.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shared_preferences.*
import kotlinx.android.synthetic.main.activity_shared_preferences.button_count
import pimsupa.test.kotlintest.R

class SharedPreferenceActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        sharedPreferences = application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        button_count.setOnClickListener {
            count += 1
            button_count.text = count.toString()
        }

        button_clear.setOnClickListener {
            sharedPreferences.edit().clear().apply()
        }
    }


//        sharedPreferences = application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

//        //put
//         sharedPreferences.edit().putInt("key",count).apply()

//        //get
//         val valueOfKey = sharedPreferences.getInt("key",0)
//        Timber.d(valueOfKey.toString())

//        //clear
//        sharedPreferences.edit().clear().apply()

}