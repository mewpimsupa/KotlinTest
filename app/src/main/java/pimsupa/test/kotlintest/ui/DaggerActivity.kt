package pimsupa.test.kotlintest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_shared_preferences.*
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.utils.dagger.MainApplication
import pimsupa.test.kotlintest.utils.dagger.MySharedPreferences
import javax.inject.Inject

/**ใช้ dagger ไม่ต้องกำหนดค่าใหม่ แค่ใช้ inject*/
class DaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var sharePref: MySharedPreferences

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

//        MainApplication.appComponent.injectDagger(this)

        button_count.setOnClickListener {
            count += 1
            button_count.text = count.toString()
        }

        button_clear.setOnClickListener {
            count = 0
            sharePref.clearSharedPreference()
        }

    }

    override fun onResume() {
        super.onResume()
        count = sharePref.getData("key").toIntOrNull()!!
        button_count.text = count.toString()
    }

    override fun onPause() {
        super.onPause()
        sharePref.putData("key", count.toString())
    }
}