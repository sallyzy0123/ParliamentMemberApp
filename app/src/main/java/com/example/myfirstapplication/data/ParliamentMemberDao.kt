package com.example.myfirstapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// date: 11 Oct 2022


// Dao is responsible for defining the methods that access the database
@Dao
interface ParliamentMemberDao {
    // get all data from table
    @Query("SELECT * FROM parliament_table")
    fun getAll(): LiveData<List<ParliamentMember>>

    // insert the data to table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: ParliamentMember)
}