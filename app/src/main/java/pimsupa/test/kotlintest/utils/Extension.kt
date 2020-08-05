package pimsupa.test.kotlintest.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.ksoap2.serialization.SoapObject
import pimsupa.test.kotlintest.R

fun Activity.toast(text:String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun View.showVisibility(isShow:Boolean){
    if (isShow) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


fun String.checkEmptyAnytype(): String {
    return if (this != "anyType{}") {
        this
    } else {
        ""
    }
}

fun SoapObject.getValueFromQuery(field: String): String {
    var value = ""
    val sfield = "; $field="
    val bfield = "{$field="
    if (this.toString().contains(sfield) || this.toString().contains(bfield)) {
        value = this.getProperty(field).toString().trim().checkEmptyAnytype()
    }
    return value
}
fun SoapObject.oneResult(): SoapObject {
    return this.getProperty("SELECT") as SoapObject
}

fun SoapObject.manyResult(): MutableList<SoapObject> {
    val list = mutableListOf<SoapObject>()
    for (i in 0 until this.propertyCount) {
        val SELECT = this.getProperty(i) as SoapObject
        list.add(SELECT)
    }
    return list
}



fun Context.showAlertDialog(
    title: String, message: String, positive: String, negative: String?,
    callback: () -> Unit
) {

    if (negative == null) {
        MaterialAlertDialogBuilder(this, R.style.myAlertDialogText)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positive) { dialog, which ->
                callback()
            }
//            .setOnDismissListener { callback() }
            .show()
    } else {
        MaterialAlertDialogBuilder(this, R.style.myAlertDialogText)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positive) { dialog, which ->
                callback()
            }
            .setNegativeButton(negative) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}

fun Context.showAlertCancelDialog(
    title: String, message: String, positive: String, negative: String,
    callback: (Boolean) -> Unit
) {

    MaterialAlertDialogBuilder(this, R.style.myAlertDialogText)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positive) { dialog, which ->
            callback(true)
        }
        .setNegativeButton(negative) { dialog, which ->
            callback(false)
        }
        .setOnDismissListener {
            callback(false)
        }
        .show()


}


fun Context.showSelectDialog(
    title: String, option: Array<String?>?,
    callback: (String) -> Unit
) {

    var selectedOption: String? = ""

    MaterialAlertDialogBuilder(this, R.style.myAlertDialogText)
        .setTitle(title)
        .setSingleChoiceItems(option, -1) { dialog, selected ->
            selectedOption = option!![selected]
            try {
                callback(selectedOption!!)
            } catch (e: Exception) {

            }
            dialog.dismiss()
        }
        .show()
}
