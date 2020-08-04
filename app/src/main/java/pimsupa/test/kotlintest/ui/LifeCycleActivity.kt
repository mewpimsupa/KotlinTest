package pimsupa.test.kotlintest.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_life_cycle.*
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.utils.toast


/**Life cycle + change activity*/
class LifeCycleActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        text_status.text = "OnCreate"

        Log.d("logtest","Create")


        button_next.setOnClickListener {
            startActivity(Intent(this, SharedPreferenceActivity::class.java))
        }

        button_count.setOnClickListener {
            count += 1
            button_count.text = count.toString()
        }

        button_toast.setOnClickListener {
            toast(edittext_first.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("logtest","Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("logtest","Pause")
    }


}