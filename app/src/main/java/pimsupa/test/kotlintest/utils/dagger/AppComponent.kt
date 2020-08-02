package pimsupa.test.kotlintest.utils.dagger

import dagger.Component
import pimsupa.test.kotlintest.ui.DaggerActivity
import pimsupa.test.kotlintest.ui.SharedPreferenceActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    // คลาสที่ต้องการจะให้ inject dependencies เข้าไป
    fun injectDagger (activity:DaggerActivity)

}
