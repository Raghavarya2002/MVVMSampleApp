package com.example.mvvvmsampleapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvvmsampleapp.data.db.entities.Quote
import com.example.mvvvmsampleapp.data.db.entities.User
import java.util.concurrent.locks.Lock

@Database(
    entities = [User::class, Quote::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao(): QuoteDao

    companion object {
        @Volatile //this variable is immediately visible to all the other threads
        private var instance: AppDatabase? = null
        private val LOCK =
            Any() //I'll use this lock to make sure we do not create  two instances of our database

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabse.db"
            ).build()


    }
}