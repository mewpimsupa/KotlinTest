package pimsupa.test.kotlintest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContainerModel(
    var containerPrefix:String = "",
    var containerNo:String = "",
    var image:Int = 0,
    var iso :String = ""
):Parcelable