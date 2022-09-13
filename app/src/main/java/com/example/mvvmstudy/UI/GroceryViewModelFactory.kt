package com.example.mvvmstudy.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmstudy.db.GroceryRepository
import java.lang.IllegalArgumentException

class GroceryViewModelFactory(private val repository: GroceryRepository) :ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryViewModel::class.java)){
            return GroceryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}