package com.example.mvvmstudy.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmstudy.db.Entity.GroceryItems
import com.example.mvvmstudy.db.GroceryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

    var amountList = MutableLiveData<List<Int>>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val tempList : List<Int> = repository.getAmount()
            withContext(Dispatchers.Main){
                amountList.value = tempList
            }
        }
    }

    fun insert(item: GroceryItems) = GlobalScope.launch {
        repository.insert(item)
    }
    fun delete(item: GroceryItems) = GlobalScope.launch {
        repository.delete(item)
    }
    fun allItems() = repository.allItems()



}