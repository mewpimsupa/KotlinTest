package pimsupa.test.kotlintest.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
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
        toast("Create")


        Log.d("logtest","123456")


        button_next.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
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
        val timer = object : CountDownTimer(1000, 1000) {
            override fun onFinish() {
                toast("Resume")
            }

            override fun onTick(millisUntilFinished: Long) {
            }
        }
        timer.start()
    }

    override fun onPause() {
        super.onPause()
        toast("Pause")
    }


}