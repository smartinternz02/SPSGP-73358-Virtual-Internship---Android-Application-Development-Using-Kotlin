package com.example.mvvmstudy.UI

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmstudy.Adapter.GroceryAdapter
import com.example.mvvmstudy.R
import com.example.mvvmstudy.databinding.ActivityMainBinding
import com.example.mvvmstudy.databinding.GroceryDialogBinding
import com.example.mvvmstudy.db.Entity.GroceryItems
import com.example.mvvmstudy.db.GroceryDb
import com.example.mvvmstudy.db.GroceryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

lateinit var mainbinding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GroceryViewModel
    lateinit var list: List<Int>
    var totalCost : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val groceryRepository = GroceryRepository(GroceryDb(this))
        val factory = GroceryViewModelFactory(groceryRepository)

        viewModel = ViewModelProvider(this,factory)[GroceryViewModel::class.java]
        val groceryAdapter = GroceryAdapter(listOf(),viewModel)
        mainbinding.recyclerVEw.layoutManager = LinearLayoutManager(this)
        mainbinding.recyclerVEw.adapter = groceryAdapter

        viewModel.allItems().observe(this, Observer {
            groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()

        })

        object {

        }
        viewModel.amountList.observe(this, Observer {
            totalCost = getTotal(it)
            if (it.isNotEmpty()) {
                totalCost = getTotal(it)
                mainbinding.mainAmountText.text = "TotalPrice : ${totalCost} Rs "
            }else{
                mainbinding.mainAmountText.text = "TotalPrice : 0Rs "
            }

        })


       /* runBlocking {
          launch {
                  list = viewModel.getAmount()
                  if (list.isNotEmpty()) {
                     totalCost = getTotal(list)
                    _root_ide_package_.com.example.mvvmstudy.UI.mainbinding.mainAmountText.text = "Bedel : ${totalCost}TL "
                 }else{
                     _root_ide_package_.com.example.mvvmstudy.UI.mainbinding.mainAmountText.text = "Bedel : 0TL "
                  }
            }
         }*/

        mainbinding.button.setOnClickListener {
            ItemDialog(this,object :DialogListener{
                override fun onAddButtonClicked(item: GroceryItems) {
                    viewModel.insert(item)
                    totalCost = totalCost?.plus(item.itemPrice)
                    mainbinding.mainAmountText.text = "TotalPrice : ${totalCost} Rs "
                }
            }).show()
        }


    }

    override fun onResume() {
        super.onResume()


    }

     fun getTotal(list: List<Int>):Int {
        if(list == null){
            return 0
        }else{
            var totalCost =0
            for (i in 0 until list.size){
                totalCost +=list[i]
            }
            return totalCost
        }
    }


}


