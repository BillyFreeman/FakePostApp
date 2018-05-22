package victor.tech.fakepostapp

import android.app.Application
import victor.tech.fakepostapp.di.Injector

class FakeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.instance.init(applicationContext)
    }
}