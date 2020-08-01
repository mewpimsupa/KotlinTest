package pimsupa.test.kotlintest.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_life_cycle.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button_count
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.utils.toast

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_count.setOnClickListener {
            count += 1
            button_count.text = count.toString()
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