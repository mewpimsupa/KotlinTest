package pimsupa.test.kotlintest.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.ksoap2.serialization.SoapObject
import pimsupa.sss.surveyin.utils.Utils
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.model.LoginModel
import pimsupa.test.kotlintest.utils.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener {
            val username = edittext_username.text.toString()
            val password = edittext_password.text.toString()
            AsynctaskLogin().execute(username,password)
        }

        button_cleaning.setOnClickListener {
            AsynctaskCleaning().execute()
        }

    }

    inner class AsynctaskLogin: AsyncTask<String, Void, SoapObject?>() {
        override fun onPreExecute() {
            loading_layout.showVisibility(true)
        }
        override fun doInBackground(vararg params: String?): SoapObject? {
            val username = params[0]
            val password = params[1]
            val sql = "select * from UserAccount " +
                    "where UserID = '$username' and Password = '$password' "
            Log.i("logtest",sql)
            return CallWebServiceTest().callApi(Utils.METHOD_GET_DATA, sql)
        }

        override fun onPostExecute(result: SoapObject?) {
            if(result != null){
                val select = result.oneResult()
                val username = select.getValueFromQuery("UserID")
                Log.i("logtest","$username login!")
            }
            else{
                Log.i("logtest","invalid username or password")
            }
            loading_layout.showVisibility(false)
        }
    }

    inner class AsynctaskCleaning: AsyncTask<Void, Void, SoapObject?>() {
        override fun onPreExecute() {
            loading_layout.showVisibility(true)
        }
        override fun doInBackground(vararg params: Void?): SoapObject? {
            val sql = "select * from Cleaning "
            Log.i("logtest",sql)
            return CallWebServiceTest().callApi(Utils.METHOD_GET_DATA, sql)
        }

        override fun onPostExecute(result: SoapObject?) {
            if(result != null){
                val select = result.manyResult()
                val list = mutableListOf<String?>()
                select.forEach {
                    list.add(it.getValueFromQuery("CleaningCode"))
                }
                //showSelectDialog
                showSelectDialog("select cleaning",list.toTypedArray()) {
                    button_cleaning.text = it
                }
            }
            loading_layout.showVisibility(false)
        }
    }

}