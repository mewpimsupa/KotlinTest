package pimsupa.test.kotlintest.respository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import pimsupa.test.kotlintest.utils.Utils
import pimsupa.test.kotlintest.utils.CallWebService
import javax.inject.Inject

class LoginRespository @Inject constructor(val webService: CallWebService) {

    suspend fun testDatabase():String = withContext(Dispatchers.IO){
        //call database
        delay(3000)
        return@withContext "Hello"
    }

    suspend fun login(username:String,password:String):Boolean = withContext(Dispatchers.IO){
        val sql = "select * from UserAccount " +
                "where UserID = '$username' and Password = '$password' "
        Log.i("logtest",sql)
        val result =  webService.callApi(Utils.METHOD_GET_DATA, sql)

        if(result != null){
            return@withContext true
        }
        else{
            return@withContext false
        }
    }

}