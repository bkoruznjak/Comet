package hr.from.bkoruznjak.comet.newplayer.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import hr.from.bkoruznjak.comet.newplayer.repository.GeoRepository
import hr.from.bkoruznjak.comet.root.database.dao.UserDao

class NewPlayerViewModel(private val geoRepository: GeoRepository,
                         private val userDao: UserDao) : ViewModel() {

    private val _clubs = MutableLiveData<Array<String>>()

    private val _countries = MutableLiveData<Array<String>>()

    val clubs: LiveData<Array<String>>
        get() = _clubs

    val countries: LiveData<Array<String>>
        get() = _countries

    fun load() {
        //check local repository
        if (geoRepository.clubList.isNotEmpty()) {
            _clubs.postValue(GeoRepository.clubList)
        }

        if (geoRepository.countryList.isNotEmpty()) {
            _countries.postValue(GeoRepository.countryList)
        }
        //if empty fire off network request

    }

    fun registerNewPlayer(uniqueId: String,
                          firstName: String,
                          lastName: String,
                          dateOfBirth: String,
                          placeOfBirth: String,
                          country: String,
                          club: String,
                          dateFrom: String,
                          dateTo: String) {

    }
}