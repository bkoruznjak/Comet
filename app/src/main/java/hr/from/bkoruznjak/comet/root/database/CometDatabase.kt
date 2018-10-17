package hr.from.bkoruznjak.comet.root.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import hr.from.bkoruznjak.comet.root.database.dao.UserDao
import hr.from.bkoruznjak.comet.root.database.model.UserDTO

@Database(entities = [
    UserDTO::class
], version = 1)
abstract class CometDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}