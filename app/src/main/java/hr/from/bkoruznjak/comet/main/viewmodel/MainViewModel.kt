package hr.from.bkoruznjak.comet.main.viewmodel

import android.arch.lifecycle.ViewModel
import hr.from.bkoruznjak.comet.main.navigation.ScreenFragmentProvider

class MainViewModel : ViewModel() {

    var currentScreenId = ScreenFragmentProvider.HOME_SCREEN

    fun load(){

    }
}