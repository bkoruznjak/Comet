package hr.from.bkoruznjak.comet.root.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import hr.from.bkoruznjak.comet.root.database.model.UserDTO

@Dao
interface UserDao {

    @Query("select * from UserDTO")
    fun getUsersLive(): LiveData<List<UserDTO>>

    @Query("select * from UserDTO")
    fun getUsers(): List<UserDTO>

    @Query("select * from UserDTO where _ID = :userId")
    fun getUserWithIdLive(userId: Long): LiveData<UserDTO>

    @Query("select * from UserDTO where _ID = :userId")
    fun getUserWithId(userId: Long): UserDTO

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: UserDTO): Long

    @Delete
    fun deleteUser(user: UserDTO)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: UserDTO)
}