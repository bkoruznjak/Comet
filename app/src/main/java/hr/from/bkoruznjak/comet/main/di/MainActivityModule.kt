package hr.from.bkoruznjak.comet.main.di

import hr.from.bkoruznjak.comet.main.navigation.ScreenFragmentProvider
import hr.from.bkoruznjak.comet.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val MainActivityModule = module {

    viewModel { MainViewModel() }
    single { ScreenFragmentProvider() }
}