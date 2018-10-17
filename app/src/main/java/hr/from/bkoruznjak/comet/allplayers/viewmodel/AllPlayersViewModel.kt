package hr.from.bkoruznjak.comet.allplayers.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import hr.from.bkoruznjak.comet.root.database.dao.UserDao
import hr.from.bkoruznjak.comet.root.database.model.UserDTO
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AllPlayersViewModel(private val usersDao: UserDao) : ViewModel() {

    private val _playerList = MutableLiveData<List<UserDTO>>()

    val playerList: LiveData<List<UserDTO>>
        get() = _playerList

    fun load() {
        doAsync {
            val databaseUsers = usersDao.getUsers()
            uiThread { _playerList.postValue(databaseUsers) }
        }
    }
}