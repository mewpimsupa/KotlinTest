package pimsupa.test.kotlintest.ui.mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_mvvm.*
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.respository.LoginRespository

class MvvmActivity : AppCompatActivity() {

    private lateinit var factory: MvvmViewmodelFactory

    private val viewmodel: MvvmViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        factory = MvvmViewmodelFactory(LoginRespository())


        viewmodel.toast.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        viewmodel.textButton.observe(this, Observer {
            it?.let {
                button_count.text  = it
            }
        })

        button_count.setOnClickListener {
            viewmodel.onClickCount()
        }

        button_connect.setOnClickListener {
            viewmodel.testCallFunction()
        }
    }

}