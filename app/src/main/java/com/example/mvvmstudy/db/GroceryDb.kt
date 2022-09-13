package com.example.mvvmstudy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmstudy.db.Entity.GroceryItems


@Database(entities = [GroceryItems::class], version = 1)
abstract class GroceryDb :RoomDatabase() {

    abstract fun getGroceryDao() : GroceryDao

    companion object{
        @Volatile
        private var instance : GroceryDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDb(context).also {
                instance = it
            }
        }

        private fun createDb(context: Context) =
            Room.databaseBuilder(context.applicationContext, GroceryDb::class.java,"GroceryDatabase.db").build()


    }
}