package pimsupa.test.kotlintest.utils.dagger

import android.app.Application
import pimsupa.test.kotlintest.BuildConfig


class MainApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var instance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()


//        // แสดง Log เฉพาะ Debug Mode
//        if (BuildConfig.DEBUG) {
//            Timber.plant(object : Timber.DebugTree() {
//                override fun createStackElementTag(element: StackTraceElement): String {
//                    return ("mew-" + super.createStackElementTag(element) + ":"
//                            + element.methodName)
//                }
//            })
//            val core = CrashlyticsCore.Builder()
//                .disabled(BuildConfig.DEBUG)
//                .build()
//            Fabric.with(this, Crashlytics.Builder().core(core).build())
//
//            Timber.plant(CrashlyticsTree())
//        }
    }

}


