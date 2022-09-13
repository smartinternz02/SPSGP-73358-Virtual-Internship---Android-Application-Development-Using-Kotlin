package com.example.mvvmstudy.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmstudy.db.Entity.GroceryItems

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryItems)

    @Delete
    suspend fun delete(item: GroceryItems)

    @Query("SELECT * FROM grocery_items")
    fun getAll(): LiveData<List<GroceryItems>>

    @Query("SELECT itemPrice FROM grocery_items")
    fun getAmount() : List<Int>
}