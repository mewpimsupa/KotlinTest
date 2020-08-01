package pimsupa.test.kotlintest.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.model.LoginModel

class LoginActivity : AppCompatActivity() {

    private lateinit var login: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login = LoginModel("test", "1")
        checkAuthen(login)

    }


    private fun checkAuthen(a: LoginModel): Boolean {
        if (a.username == "test" && a.password == "test") {
            Log.d("logtest", "Correct!")
            return true
        } else {
            Log.d("logtest", "Invalid")
            return false
        }
    }



}