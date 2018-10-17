package hr.from.bkoruznjak.comet.newplayer.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import hr.from.bkoruznjak.comet.newplayer.repository.GeoRepository
import hr.from.bkoruznjak.comet.root.database.dao.UserDao
import hr.from.bkoruznjak.comet.root.database.model.UserDTO
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NewPlayerViewModel(private val geoRepository: GeoRepository,
                         private val userDao: UserDao) : ViewModel() {

    private val _clubs = MutableLiveData<Array<String>>()

    private val _countries = MutableLiveData<Array<String>>()

    private val _newPlayer = MutableLiveData<UserDTO>()

    val clubs: LiveData<Array<String>>
        get() = _clubs

    val countries: LiveData<Array<String>>
        get() = _countries

    val newPlayer: LiveData<UserDTO>
        get() = _newPlayer

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

        doAsync {
            val playerForInsert = UserDTO(uniqueId = uniqueId,
                    firstName = firstName,
                    lastName = lastName,
                    dateOfBirth = dateOfBirth,
                    placeOfBirth = placeOfBirth,
                    country = country,
                    club = club,
                    dateFrom = dateFrom,
                    dateTo = dateTo)
            val id = userDao.insertUser(playerForInsert)
            val copyWithId = playerForInsert.copy(id = id)
            uiThread { _newPlayer.postValue(copyWithId) }
        }
    }
}