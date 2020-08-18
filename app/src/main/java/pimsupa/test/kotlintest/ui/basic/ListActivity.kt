package pimsupa.test.kotlintest.ui.basic

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import kotlinx.android.synthetic.main.activity_list.*
import pimsupa.test.kotlintest.utils.Utils
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.adapter.ContainerAdapter
import pimsupa.test.kotlintest.model.ContainerModel
import pimsupa.test.kotlintest.utils.requestPermission
import pimsupa.test.kotlintest.utils.toast
import java.io.File
import java.io.IOException

class ListActivity : AppCompatActivity() {

    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        sharedPref =  application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val list = mutableListOf<ContainerModel>()

        var data = ContainerModel()
        data.containerPrefix = "BEAU"
        data.containerNo = "2656340"
        data.iso = "2230"
        data.image = R.drawable.ic_account_circle_black_24dp
        list.add(data)

        data = ContainerModel()
        data.containerPrefix = "AABB"
        data.containerNo = "1234567"
        data.iso = "22G1"
        data.image = R.drawable.ic_expand_more_black_24dp
        list.add(data)



        val adapter  = ContainerAdapter(list){
            toast(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }




}