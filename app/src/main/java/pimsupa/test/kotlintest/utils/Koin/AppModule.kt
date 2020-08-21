package pimsupa.test.kotlintest.utils.Koin

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pimsupa.test.kotlintest.respository.LoginRespository
import pimsupa.test.kotlintest.ui.mvvmDagger.MvvmViewmodelFactory
import pimsupa.test.kotlintest.utils.CallWebService
import pimsupa.test.kotlintest.utils.dagger.MySharedPreferences

val module = module {
    single {
        androidContext().getSharedPreferences(
            "myPrefs",
            Context.MODE_PRIVATE
        )
    }
    single {
        MySharedPreferences(get())
    }
    single {
        CallWebService(get())
    }
    single {
        LoginRespository(get())
    }
    single {
        MvvmViewmodelFactory(get())
    }

}