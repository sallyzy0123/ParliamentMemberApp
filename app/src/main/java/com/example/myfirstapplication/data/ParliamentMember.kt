package com.example.myfirstapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// date: 11 Oct 2022

// data class defines a parliament member table which includes properties.
// The property names of this data class are used by Moshi to match the names of values in JSON.
@Entity(tableName = "parliament_table")
data class ParliamentMember (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "heteka_id") val hetekaId: Int,
    @ColumnInfo(name = "seat_number") val seatNumber: Int = 0,
    @ColumnInfo(name = "last_name")val lastname: String,
    @ColumnInfo(name = "first_name")val firstname: String,
    @ColumnInfo(name = "party")val party: String,
    @ColumnInfo(name = "minister")val minister: Boolean = false,
    @ColumnInfo(name = "picture_url")val pictureUrl: String = ""
    )

