package com.example.mvvmstudy.UI

import com.example.mvvmstudy.db.Entity.GroceryItems

interface DialogListener {

    fun onAddButtonClicked(item:GroceryItems)

}