package pimsupa.sss.surveyin.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.inputmethod.InputMethodManager
import java.io.File

object Utils {
    var PACKAGE_NAME = "pimsupa.sss.surveyin"
    val app_version_on_firebase = "surveyin_version"
    val file_name_apk = "SSS-SurveyIn.apk"
    val pathNameToSaveImage = "SSS SurveyIn"
    val ftp_directory = "MR"
    val ftp_folder_app = "Estimate"
    val SOAP_NAMESPACE = "http://tempuri.org/"
    var TESTER = "user test"
    var CLIENT = "user client"
    val METHOD_GET_DATA = "GetData"
    val METHOD_SAVE_DATA = "ExecNonQuery"

    const val READ_WRITE_STORAGE = 52
    const val CAMERA_TAKE_A_PHOTO = 17
    const val AFTER_RECIEVE_DRAWN_PHOTO = 16
    const val DAMAGE_ACTIVITY = 1221
    const val RESULT_CAMERA = 11
    const val RESULT_DRAW = 12

    private val root = Environment.getExternalStorageDirectory().toString()
    val draw_directory = "$root/$pathNameToSaveImage/save_images"
    var draw_file_name = "jpg_${System.currentTimeMillis()}.jpg"
    val damage_directory = "$root/$pathNameToSaveImage/damage"
    val take_photo_provider = "$PACKAGE_NAME.fileprovider"
    val container_directory = "$root/$pathNameToSaveImage/containerNo"
    val container_file_name = "ocr_con_no.jpg"

    fun hideKeyboard(activity: Activity?) {
        if (activity != null && activity.window != null && activity.window.decorView != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm!!.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }


    fun setformatOfOneUnitDateTime(string: String): String {
//        1675 ->01060705
        var format = string
        if (string.length == 1) {
            format = "0$string"
        }
        return format
    }

    fun setformatOfOneUnitMonth(month: Int): String {
//        1675 ->01060705
        var correctMonth = month + 1
        var format = correctMonth.toString()
        if (format.length == 1) {
            format = "0$correctMonth"
        }
        return format
    }


    fun getImageFromPath(context: Context, imagePath: String?): Bitmap? {
        return MediaStore.Images.Media.getBitmap(
            context.contentResolver,
            Uri.fromFile(File(imagePath!!))
        )
    }

    fun isDigitCorrect(containerPrefix: String, containerNo: String): Boolean {

        val PFChar = PFChar
        val PFNo = PFNo
        val PFSum = PFSum
        val NoTable1 = NoTable1
        val NoTable2 = NoTable2
        val NoTable3 = NoTable3
        val NoTable4 = NoTable4
        val NoTable5 = NoTable5
        val NoTable6 = NoTable6
        val DigitTable = DigitTable

        var result = true
        var lnIndex: Int
        var lnCount1: Int
        var lnCount2: Int
        var lnPfSum: Int
        var lcPfChar: String
        var lnNoChar: Int

        lnPfSum = 0
        lcPfChar = containerPrefix.substring(0, 1)
        lnIndex = 1
        while (lnIndex <= 10) {
            if (PFChar[lnIndex].contains(lcPfChar)) {
                lnPfSum += PFNo[1][lnIndex]
            }
            lnIndex++
        }
        lcPfChar = containerPrefix.substring(1, 2)
        lnIndex = 1
        while (lnIndex <= 10) {
            if (PFChar[lnIndex].contains(lcPfChar)) {
                lnPfSum += PFNo[2][lnIndex]
            }
            lnIndex++
        }
        lcPfChar = containerPrefix.substring(2, 3)
        lnIndex = 1
        while (lnIndex <= 10) {
            if (PFChar[lnIndex].contains(lcPfChar)) {
                lnPfSum += PFNo[3][lnIndex]
            }
            lnIndex++
        }
        lcPfChar = containerPrefix.substring(3, 4)
        lnIndex = 1
        while (lnIndex <= 10) {
            if (PFChar[lnIndex].contains(lcPfChar)) {
                lnPfSum += PFNo[4][lnIndex]
            }
            lnIndex++
        }

        lnIndex = 1
        lnCount1 = 1
        breakLoop@ while (lnCount1 <= 11) {
            lnCount2 = 1
            while (lnCount2 <= 4) {
                if (lnPfSum == PFSum[lnCount1][lnCount2]) {
                    lnIndex = lnCount1
                    break@breakLoop
                }
                lnCount2++
            }
            lnCount1++
        }
        try {
            //int myNum = Integer.parseInt(containerNo);
            lnNoChar = Integer.parseInt(containerNo.substring(0, 1))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable1[lnIndex][lnCount1]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(1, 2))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable2[lnCount1][lnIndex]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(2, 3))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable3[lnIndex][lnCount1]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(3, 4))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable4[lnCount1][lnIndex]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(4, 5))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable5[lnIndex][lnCount1]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(5, 6))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable6[lnCount1][lnIndex]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }

            if (DigitTable[lnIndex] != containerNo.substring(6, 7)) {
                result = false
            }
            return result
        } catch (nfe: NumberFormatException) {
            //System.out.println("Could not parse " + nfe);
            return false
        }

    }

    fun makeFragmentName(viewId: Int, id: Int): String {
        return "android:switcher:$viewId:$id"
    }

    fun setFormatDate(string: String): String {
        val date = string.split('T', '+')[0] + " " + string.split('T', '+')[1].substring(0, 5)
        return date.trim()
    }

    fun setFormatDatePickerDialog(string: String): String {
        //202001060705
        val year = string.substring(0, 4)
        val month = string.substring(4, 6)
        val day = string.substring(6, 8)
        val hour = string.substring(8, 10)
        val minute = string.substring(10, 12)
        val date = "${year}-${month}-${day} $hour:$minute"
        return date.trim()
    }

    fun setFormatDateSave(string: String): String {
//        2019-12-11 15:00  ->  2019-12-11 15:00:00
        var date = ""
        if (string != "") {
            date = "$string:00"
        }
        return date.trim()
    }


    private fun formatMonth(month: String): String {
        var result = ""
        when (month.trim()) {
            "Jan" -> {
                result = "01"
            }
            "Feb" -> {
                result = "02"
            }
            "Mar" -> {
                result = "03"
            }
            "Apr" -> {
                result = "04"
            }
            "May" -> {
                result = "05"
            }
            "Jun" -> {
                result = "06"
            }
            "Jul" -> {
                result = "07"
            }
            "Aug" -> {
                result = "08"
            }
            "Sep" -> {
                result = "09"
            }
            "Oct" -> {
                result = "10"
            }
            "Nov" -> {
                result = "11"
            }
            "Dec" -> {
                result = "12"
            }
        }
        return result
    }

    fun isInternetConnected(context: Context): Boolean {
        val connMgr = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun checkRotating(bitmap: Bitmap?, imagePath: String?): Bitmap? {
        bitmap?.let {
            val ei = ExifInterface(imagePath)
            val orientation = ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
            )

            var rotatedBitmap: Bitmap? = null
            when (orientation) {

                ExifInterface.ORIENTATION_ROTATE_90 -> rotatedBitmap = setOreintation(
                    bitmap,
                    90f
                )

                ExifInterface.ORIENTATION_ROTATE_180 -> rotatedBitmap = setOreintation(
                    bitmap,
                    180f
                )

                ExifInterface.ORIENTATION_ROTATE_270 -> rotatedBitmap = setOreintation(
                    bitmap,
                    270f
                )

                ExifInterface.ORIENTATION_NORMAL -> rotatedBitmap = bitmap
                else -> rotatedBitmap = bitmap
            }
            return rotatedBitmap
        }
        return bitmap
    }

    private fun setOreintation(bitmap: Bitmap, degree: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun createImageFile(directory: String, fileName: String, callback: (String) -> Unit): File {
        draw_file_name = "jpg_${System.currentTimeMillis()}.jpg"
        val myDir = File(directory)
        if (!myDir.exists()) {
            myDir.mkdirs()
        }
        val fname = fileName
        val file = File(myDir, fname)
        if (file.exists()) file.delete()
        file.createNewFile()
        callback(file.absolutePath)
        return file
    }


    /**check digit container number*/
    val DigitTable = arrayOf("0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
    val PFChar = arrayOf("", "BLV", "CMW", "DNX", "EOY", "FPZ", "GQ", "HR", "IS", "JT", "KUA")

    val PFNo = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
        intArrayOf(0, 2, 4, 6, 8, 10, 1, 3, 5, 7, 9),
        intArrayOf(0, 4, 8, 1, 5, 9, 2, 6, 10, 3, 7),
        intArrayOf(0, 8, 5, 2, 10, 7, 4, 1, 9, 6, 3)
    )

    val PFSum = arrayOf(
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(0, 0, 11, 22, 33),
        intArrayOf(0, 0, 12, 23, 34),
        intArrayOf(0, 0, 13, 24, 35),
        intArrayOf(0, 3, 14, 25, 36),
        intArrayOf(0, 4, 15, 26, 37),
        intArrayOf(0, 5, 16, 27, 38),
        intArrayOf(0, 6, 17, 28, 39),
        intArrayOf(0, 7, 18, 29, 40),
        intArrayOf(0, 8, 19, 30, 0),
        intArrayOf(0, 9, 20, 31, 0),
        intArrayOf(0, 10, 21, 32, 0)
    )

    val NoTable1 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 9, 7, 5, 3, 1, 11, 8, 6, 4, 2),
        intArrayOf(0, 2, 0, 9, 7, 5, 3, 1, 11, 8, 6, 4),
        intArrayOf(0, 4, 2, 0, 9, 7, 5, 3, 1, 11, 8, 6),
        intArrayOf(0, 6, 4, 2, 0, 9, 7, 5, 3, 1, 11, 8),
        intArrayOf(0, 8, 6, 4, 2, 0, 9, 7, 5, 3, 1, 11),
        intArrayOf(0, 11, 8, 6, 4, 2, 0, 9, 7, 5, 3, 1),
        intArrayOf(0, 1, 11, 8, 6, 4, 2, 0, 9, 7, 5, 3),
        intArrayOf(0, 3, 1, 11, 8, 6, 4, 2, 0, 9, 7, 5),
        intArrayOf(0, 5, 3, 1, 11, 8, 6, 4, 2, 0, 9, 7),
        intArrayOf(0, 7, 5, 3, 1, 11, 8, 6, 4, 2, 0, 9),
        intArrayOf(0, 9, 7, 5, 3, 1, 11, 8, 6, 4, 2, 0)
    )

    val NoTable2 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11),
        intArrayOf(0, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
        intArrayOf(0, 9, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8),
        intArrayOf(0, 8, 9, 11, 0, 1, 2, 3, 4, 5, 6, 7),
        intArrayOf(0, 7, 8, 9, 11, 0, 1, 2, 3, 4, 5, 6),
        intArrayOf(0, 6, 7, 8, 9, 11, 0, 1, 2, 3, 4, 5),
        intArrayOf(0, 5, 6, 7, 8, 9, 11, 0, 1, 2, 3, 4),
        intArrayOf(0, 4, 5, 6, 7, 8, 9, 11, 0, 1, 2, 3),
        intArrayOf(0, 3, 4, 5, 6, 7, 8, 9, 11, 0, 1, 2),
        intArrayOf(0, 2, 3, 4, 5, 6, 7, 8, 9, 11, 0, 1),
        intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 0)
    )

    val NoTable3 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 5, 11, 4, 9, 3, 8, 2, 7, 1, 6),
        intArrayOf(0, 6, 0, 5, 11, 4, 9, 3, 8, 2, 7, 1),
        intArrayOf(0, 1, 6, 0, 5, 11, 4, 9, 3, 8, 2, 7),
        intArrayOf(0, 7, 1, 6, 0, 5, 11, 4, 9, 3, 8, 2),
        intArrayOf(0, 2, 7, 1, 6, 0, 5, 11, 4, 9, 3, 8),
        intArrayOf(0, 8, 2, 7, 1, 6, 0, 5, 11, 4, 9, 3),
        intArrayOf(0, 3, 8, 2, 7, 1, 6, 0, 5, 11, 4, 9),
        intArrayOf(0, 9, 3, 8, 2, 7, 1, 6, 0, 5, 11, 4),
        intArrayOf(0, 4, 9, 3, 8, 2, 7, 1, 6, 0, 5, 11),
        intArrayOf(0, 11, 4, 9, 3, 8, 2, 7, 1, 6, 0, 5),
        intArrayOf(0, 5, 11, 4, 9, 3, 8, 2, 7, 1, 6, 0)
    )

    val NoTable4 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 3, 6, 9, 1, 4, 7, 11, 2, 5, 8),
        intArrayOf(0, 8, 0, 3, 6, 9, 1, 4, 7, 11, 2, 5),
        intArrayOf(0, 5, 8, 0, 3, 6, 9, 1, 4, 7, 11, 2),
        intArrayOf(0, 2, 5, 8, 0, 3, 6, 9, 1, 4, 7, 11),
        intArrayOf(0, 11, 2, 5, 8, 0, 3, 6, 9, 1, 4, 7),
        intArrayOf(0, 7, 11, 2, 5, 8, 0, 3, 6, 9, 1, 4),
        intArrayOf(0, 4, 7, 11, 2, 5, 8, 0, 3, 6, 9, 1),
        intArrayOf(0, 1, 4, 7, 11, 2, 5, 8, 0, 3, 6, 9),
        intArrayOf(0, 9, 1, 4, 7, 11, 2, 5, 8, 0, 3, 6),
        intArrayOf(0, 6, 9, 1, 4, 7, 11, 2, 5, 8, 0, 3),
        intArrayOf(0, 3, 6, 9, 1, 4, 7, 11, 2, 5, 8, 0)
    )

    val NoTable5 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 4, 8, 1, 5, 9, 2, 6, 11, 3, 7),
        intArrayOf(0, 7, 0, 4, 8, 1, 5, 9, 2, 6, 11, 3),
        intArrayOf(0, 3, 7, 0, 4, 8, 1, 5, 9, 2, 6, 11),
        intArrayOf(0, 11, 3, 7, 0, 4, 8, 1, 5, 9, 2, 6),
        intArrayOf(0, 6, 11, 3, 7, 0, 4, 8, 1, 5, 9, 2),
        intArrayOf(0, 2, 6, 11, 3, 7, 0, 4, 8, 1, 5, 9),
        intArrayOf(0, 9, 2, 6, 11, 3, 7, 0, 4, 8, 1, 5),
        intArrayOf(0, 5, 9, 2, 6, 11, 3, 7, 0, 4, 8, 1),
        intArrayOf(0, 1, 5, 9, 2, 6, 11, 3, 7, 0, 4, 8),
        intArrayOf(0, 8, 1, 5, 9, 2, 6, 11, 3, 7, 0, 4),
        intArrayOf(0, 4, 8, 1, 5, 9, 2, 6, 11, 3, 7, 0)
    )

    val NoTable6 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 9, 7, 5, 3, 1, 11, 8, 6, 4, 2),
        intArrayOf(0, 2, 0, 9, 7, 5, 3, 1, 11, 8, 6, 4),
        intArrayOf(0, 4, 2, 0, 9, 7, 5, 3, 1, 11, 8, 6),
        intArrayOf(0, 6, 4, 2, 0, 9, 7, 5, 3, 1, 11, 8),
        intArrayOf(0, 8, 6, 4, 2, 0, 9, 7, 5, 3, 1, 11),
        intArrayOf(0, 11, 8, 6, 4, 2, 0, 9, 7, 5, 3, 1),
        intArrayOf(0, 1, 11, 8, 6, 4, 2, 0, 9, 7, 5, 3),
        intArrayOf(0, 3, 1, 11, 8, 6, 4, 2, 0, 9, 7, 5),
        intArrayOf(0, 5, 3, 1, 11, 8, 6, 4, 2, 0, 9, 7),
        intArrayOf(0, 7, 5, 3, 1, 11, 8, 6, 4, 2, 0, 9),
        intArrayOf(0, 9, 7, 5, 3, 1, 11, 8, 6, 4, 2, 0)
    )

    fun ContainerDigit(containerPrefix: String, containerNo: String): Boolean {

        val PFChar = PFChar
        val PFNo = PFNo
        val PFSum = PFSum
        val NoTable1 = NoTable1
        val NoTable2 = NoTable2
        val NoTable3 = NoTable3
        val NoTable4 = NoTable4
        val NoTable5 = NoTable5
        val NoTable6 = NoTable6
        val DigitTable = DigitTable

        var result = true
        var lnIndex: Int
        var lnCount1: Int
        var lnCount2: Int
        var lnPfSum: Int
        var lcPfChar: String
        var lnNoChar: Int

        lnPfSum = 0
        lcPfChar = containerPrefix.substring(0, 1)
        lnIndex = 1
        while (lnIndex <= 10) {
            if (PFChar[lnIndex].contains(lcPfChar)) {
                lnPfSum += PFNo[1][lnIndex]
            }
            lnIndex++
        }
        lcPfChar = containerPrefix.substring(1, 2)
        lnIndex = 1
        while (lnIndex <= 10) {
            if (PFChar[lnIndex].contains(lcPfChar)) {
                lnPfSum += PFNo[2][lnIndex]
            }
            lnIndex++
        }
        lcPfChar = containerPrefix.substring(2, 3)
        lnIndex = 1
        while (lnIndex <= 10) {
            if (PFChar[lnIndex].contains(lcPfChar)) {
                lnPfSum += PFNo[3][lnIndex]
            }
            lnIndex++
        }
        lcPfChar = containerPrefix.substring(3, 4)
        lnIndex = 1
        while (lnIndex <= 10) {
            if (PFChar[lnIndex].contains(lcPfChar)) {
                lnPfSum += PFNo[4][lnIndex]
            }
            lnIndex++
        }

        lnIndex = 1
        lnCount1 = 1
        breakLoop@ while (lnCount1 <= 11) {
            lnCount2 = 1
            while (lnCount2 <= 4) {
                if (lnPfSum == PFSum[lnCount1][lnCount2]) {
                    lnIndex = lnCount1
                    break@breakLoop
                }
                lnCount2++
            }
            lnCount1++
        }
        try {
            //int myNum = Integer.parseInt(containerNo);
            lnNoChar = Integer.parseInt(containerNo.substring(0, 1))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable1[lnIndex][lnCount1]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(1, 2))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable2[lnCount1][lnIndex]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(2, 3))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable3[lnIndex][lnCount1]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(3, 4))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable4[lnCount1][lnIndex]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(4, 5))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable5[lnIndex][lnCount1]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }
            lnNoChar = Integer.parseInt(containerNo.substring(5, 6))
            lnCount1 = 1
            while (lnCount1 <= 11) {
                if (lnNoChar == NoTable6[lnCount1][lnIndex]) {
                    lnIndex = lnCount1
                    break
                }
                lnCount1++
            }

            if (DigitTable[lnIndex] != containerNo.substring(6, 7)) {
                result = false
            }
            return result
        } catch (nfe: NumberFormatException) {
            //System.out.println("Could not parse " + nfe);
            return false
        }

    }


}
