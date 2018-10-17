package hr.from.bkoruznjak.comet.root.database.di

import android.arch.persistence.room.Room
import hr.from.bkoruznjak.comet.root.database.CometDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val DatabaseModule: Module = module {
    single {
        Room.databaseBuilder(androidApplication(), CometDatabase::class.java, "comet-android-db").build()
    }

    single { get<CometDatabase>().userDao() }
}