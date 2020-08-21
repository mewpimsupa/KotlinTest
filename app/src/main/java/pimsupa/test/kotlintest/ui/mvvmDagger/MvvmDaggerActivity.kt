package pimsupa.test.kotlintest.ui.mvvmDagger

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_mvvm.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject
import pimsupa.test.kotlintest.R
import pimsupa.test.kotlintest.respository.LoginRespository
import pimsupa.test.kotlintest.utils.CallWebService
import pimsupa.test.kotlintest.utils.dagger.MainApplication
import javax.inject.Inject

class MvvmDaggerActivity : AppCompatActivity() {

    private val factory: MvvmViewmodelFactory  by inject()
    private val viewmodel: MvvmViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        viewmodel.toast.observe(this, Observer { e ->
            e.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewmodel.textButton.observe(this, Observer {
            button_login.text = it
        })

        button_login.setOnClickListener {
            viewmodel.onClickLogin(
                edittext_username.text.toString(),
                edittext_password.text.toString()
            )
        }
    }

}