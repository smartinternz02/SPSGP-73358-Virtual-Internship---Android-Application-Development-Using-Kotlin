package com.example.mvvmstudy.db

import com.example.mvvmstudy.db.Entity.GroceryItems

class GroceryRepository(private val db: GroceryDb) {

    suspend fun insert(item: GroceryItems) = db.getGroceryDao().insert(item)
    suspend fun delete(item: GroceryItems) = db.getGroceryDao().delete(item)

    suspend fun getAmount() = db.getGroceryDao().getAmount()
    fun allItems() = db.getGroceryDao().getAll()


}