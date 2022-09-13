package com.example.mvvmstudy.db.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "grocery_items")
data class GroceryItems(
    @ColumnInfo(name = "itemName")
    val itemName: String,
    @ColumnInfo(name = "itemQuantity")
    val itemQuantity: Int,
    @ColumnInfo(name = "itemPrice")
    val itemPrice: Int,

)
{
    @PrimaryKey(autoGenerate = true)
    var id :Int? =null
}