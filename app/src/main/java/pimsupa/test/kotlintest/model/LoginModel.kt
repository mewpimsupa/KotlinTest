package pimsupa.test.kotlintest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
    var userID:String,
    var yard:String)
    :Parcelable