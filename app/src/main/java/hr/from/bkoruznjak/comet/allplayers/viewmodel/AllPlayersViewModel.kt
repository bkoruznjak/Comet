package hr.from.bkoruznjak.comet.allplayers.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import hr.from.bkoruznjak.comet.root.database.dao.UserDao
import hr.from.bkoruznjak.comet.root.database.model.UserDTO
import org.jetbrains.anko.doAsync

class AllPlayersViewModel(private val usersDao: UserDao) : ViewModel() {

    private val _playerList = MutableLiveData<List<UserDTO>>()

    private var databaseUsers: List<UserDTO> = ArrayList()

    var filter: String = ""

    val playerList: LiveData<List<UserDTO>>
        get() = _playerList

    fun load() {
        if (databaseUsers.isEmpty()) {
            doAsync {
                databaseUsers = usersDao.getUsers()
                filterPlayersByName(filter)
            }
        }
    }

    fun filterPlayersByName(filterText: CharSequence?) {
        val filtered = if (filterText.isNullOrBlank()) {
            databaseUsers
        } else {
            val filtered = databaseUsers.filter {
                it.firstName.contains(filterText!!, true) ||
                        it.lastName.contains(filterText, true)
            }
            filtered
        }
        _playerList.postValue(filtered)
    }
}