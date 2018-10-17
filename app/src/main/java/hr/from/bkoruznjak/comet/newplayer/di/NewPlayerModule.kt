package hr.from.bkoruznjak.comet.newplayer.di

import hr.from.bkoruznjak.comet.newplayer.repository.GeoRepository
import hr.from.bkoruznjak.comet.newplayer.viewmodel.NewPlayerViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val NewPlayerModule: Module = module {

    single { GeoRepository }
    viewModel { NewPlayerViewModel(get(), get()) }
}