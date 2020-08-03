package pimsupa.test.kotlintest.respository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginRespository {

    suspend fun testDatabase():String = withContext(Dispatchers.IO){
        //call database
        delay(3000)
        return@withContext "Hello"
    }

}