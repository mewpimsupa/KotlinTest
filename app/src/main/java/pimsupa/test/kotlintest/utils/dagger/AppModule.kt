package pimsupa.test.kotlintest.utils.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class PrimaryPref


    @Singleton
    @Provides
    fun provideApplication() = application


//    @Singleton
//    @Provides
//    fun provideCamera() = Camera(application)

    @Singleton
    @Provides
    @PrimaryPref
    fun provideSharedPreferences() = application.getSharedPreferences(
        "myPrefs",
        Context.MODE_PRIVATE
    )

//    @Singleton
//    @Provides
//    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
//        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
//        val configSettings = FirebaseRemoteConfigSettings.Builder()
//            .setDeveloperModeEnabled(BuildConfig.DEBUG)
//            .build()
//        mFirebaseRemoteConfig.setConfigSettings(configSettings)
//        return mFirebaseRemoteConfig
//    }

//    @Singleton
//    @Provides
//    fun webService(remote: FirebaseRemoteConfig, sharedPred: MySharedPreferences) =
//        CallWebService(remote, sharedPred)


//    @Singleton
//    fun roomDatabase() = AppDatabase.getInstance(application)

}