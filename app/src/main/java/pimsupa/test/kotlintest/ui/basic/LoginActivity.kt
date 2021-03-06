package pimsupa.test.kotlintest.ui.basic

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.ksoap2.serialization.SoapObject
import pimsupa.test.kotlintest.utils.Utils
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.model.LoginModel
import pimsupa.test.kotlintest.utils.*

class LoginActivity : AppCompatActivity() {

private lateinit var sharePref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1222)



        sharePref =  application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        if(sharePref.getSharedPref<LoginModel>()!= null){
            val userID = sharePref.getSharedPref<LoginModel>()!!.userID
            toast(userID)
        }

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

                val item = LoginModel(username,"1")

                sharePref.saveSharedPref(item)

                startActivity(Intent(this@LoginActivity,ListActivity::class.java))
            }
            else{
                Log.i("logtest","invalid username or password")

                sharePref.edit().clear().apply()
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