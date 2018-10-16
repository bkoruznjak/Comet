package hr.from.bkoruznjak.comet.root

import android.app.Application
import hr.from.bkoruznjak.comet.main.di.MainActivityModule
import org.koin.android.ext.android.startKoin

class CometApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            MainActivityModule
        ))
    }
}