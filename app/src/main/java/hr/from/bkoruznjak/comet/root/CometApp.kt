package hr.from.bkoruznjak.comet.root

import android.app.Application
import hr.from.bkoruznjak.comet.main.di.MainActivityModule
import hr.from.bkoruznjak.comet.newplayer.di.NewPlayerModule
import hr.from.bkoruznjak.comet.root.database.di.DatabaseModule
import org.koin.android.ext.android.startKoin

class CometApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                DatabaseModule,
                MainActivityModule,
                NewPlayerModule
        ))
    }
}