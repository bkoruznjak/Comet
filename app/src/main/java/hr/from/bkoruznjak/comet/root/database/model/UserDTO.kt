package hr.from.bkoruznjak.comet.root.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class UserDTO(@ColumnInfo(name = "_ID")
                   @PrimaryKey(autoGenerate = true) val id: Long,
                   @ColumnInfo(name = "UNIQUE_ID") val uniqueId: String,
                   @ColumnInfo(name = "FIRST_NAME") val firstName: String,
                   @ColumnInfo(name = "LAST_NAME") val lastName: String,
                   @ColumnInfo(name = "DATE_OF_BIRTH") val dateOfBirth: String,
                   @ColumnInfo(name = "PLACE_OF_BIRTH") val placeOfBirth: String,
                   @ColumnInfo(name = "COUNTRY") val country: String,
                   @ColumnInfo(name = "CLUB") val club: String,
                   @ColumnInfo(name = "DATE_FROM") val dateFrom: String,
                   @ColumnInfo(name = "DATE_TO") val dateTo: String)