package hr.from.bkoruznjak.comet.allplayers.di

import hr.from.bkoruznjak.comet.allplayers.viewmodel.AllPlayersViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val AllPlayersModule: Module = module {

    viewModel { AllPlayersViewModel(get()) }
}