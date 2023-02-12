package com.example.myfirstapplication.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstapplication.MemberApplication

// date: 11 Oct 2022

// Contains the database holder and serves as the main access point
// for the underlying connection to app's data.
@Database(entities = [ParliamentMember::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract val parliamentMemberDao: ParliamentMemberDao

    // the companion object is visible to other classes
    companion object {

        // Volatile means INSTANCE is visible to other thread
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // use instance to build the database
        fun getInstance(): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MemberApplication.appContext,
                        AppDatabase::class.java,
                        "app_database")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}