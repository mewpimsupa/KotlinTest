package pimsupa.test.kotlintest.utils

import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import pimsupa.sss.surveyin.utils.Utils
import pimsupa.sss.surveyin.utils.Utils.METHOD_GET_DATA
import pimsupa.test.kotlintest.utils.dagger.MySharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

class CallWebService () {

    private var NewDataSet: SoapObject? = null
    private var Return: SoapObject? = null


//    private fun getUserType(): String {
//        return sharedPref.getData("UserType")
//    }

//    private fun getWebservice(): String? {
//        val type = when (getUserType()) {
//            Utils.TESTER -> "web_service_test"
//            else -> "web_service_r"
//        }
////        val type = "web_service_test"
//        return mFirebaseRemoteConfig.getString(type)
//    }

    fun callApi(methodName: String, sql: String?): SoapObject? {
//        val SOAP_URL = getWebservice()
        val SOAP_URL = "http://111.223.34.195/SSSServices/Service.asmx?WSDL"
        val SOAP_ACTION = Utils.SOAP_NAMESPACE + methodName
        val soapObject = SoapObject(Utils.SOAP_NAMESPACE, methodName)
        val httpTransportSE = HttpTransportSE(SOAP_URL)  //ดึงจาก pref

        soapObject.addProperty("psSQL", sql)

        NewDataSet = null
        Return = null
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(soapObject)
        envelope.dotNet = true

        try {
            httpTransportSE.call(SOAP_ACTION, envelope)

            if (methodName == METHOD_GET_DATA) {
                val Response = envelope.bodyIn as SoapObject
                val GetDataResult = Response.getProperty("GetDataResult") as SoapObject
                val diffgram = GetDataResult.getProperty("diffgram") as SoapObject
                NewDataSet = diffgram.getProperty("NewDataSet") as SoapObject
            } else {
                val Response = envelope.bodyIn as SoapObject
                val ExecNonQueryResult = Response.getProperty("ExecNonQueryResult") as SoapObject
                val diffgram = ExecNonQueryResult.getProperty("diffgram") as SoapObject
                val EXECUTE = diffgram.getProperty("EXECUTE") as SoapObject
                Return = EXECUTE.getProperty("Return") as SoapObject
            }

        } catch (e: Exception) {
//            Timber.d("not receive data from service")
            e.printStackTrace()
        }

        return if (methodName == METHOD_GET_DATA) {
            NewDataSet
        } else {
            Return
        }

    }
}