package pimsupa.test.kotlintest.ui

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_call_service.*
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.utils.showVisibility
import org.ksoap2.serialization.SoapObject
import pimsupa.sss.surveyin.utils.Utils
import pimsupa.test.kotlintest.utils.CallWebService

class CallServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_service)

        button_login.setOnClickListener {
            val username = edittext_username.text.toString()
            val password = edittext_password.text.toString()
            AsynctaskLogin().execute(username,password)
        }
    }

    inner class AsynctaskLogin:AsyncTask<String,Void,SoapObject?>() {
        override fun onPreExecute() {
            loading.showVisibility(true)
        }
        override fun doInBackground(vararg params: String?): SoapObject? {
            val username = params[0]
            val password = params[1]
            val sql = "select * from UserAccount " +
                    "where UserID = '$username' and Password = '$password' "
            Log.i("logtest",sql)
            return CallWebService().callApi(Utils.METHOD_GET_DATA, sql)
        }

        override fun onPostExecute(result: SoapObject?) {
            if(result != null){
                Log.i("logtest","login!")
            }
            else{
                Log.i("logtest","invalid username or password")
            }
            loading.showVisibility(false)
        }
    }
}