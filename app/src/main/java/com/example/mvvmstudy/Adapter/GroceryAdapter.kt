package com.example.mvvmstudy.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmstudy.R
import com.example.mvvmstudy.UI.GroceryViewModel
import com.example.mvvmstudy.UI.mainbinding
import com.example.mvvmstudy.databinding.ActivityMainBinding
import com.example.mvvmstudy.databinding.GroceryRecyclerViewBinding
import com.example.mvvmstudy.db.Entity.GroceryItems


class GroceryAdapter(var list: List<GroceryItems>,val viewModel: GroceryViewModel):
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>()


{
    inner class GroceryViewHolder(val binding: GroceryRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val binding = GroceryRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GroceryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        var currentPosition = list[position]
        holder.binding.itemNameText.text = currentPosition.itemName
        holder.binding.itemPriceText.text = "Price : ${currentPosition.itemPrice}Rs"
        holder.binding.itemAmountText.text = "Qty : ${currentPosition.itemQuantity}"
        holder.binding.deleteButton.setOnClickListener {
            var cost = mainbinding.mainAmountText.text.toString()
            val mString = cost!!.split(" ").toTypedArray()
            cost = mString[2]
            var anan = cost.toInt()
            anan -= currentPosition.itemPrice
            mainbinding.mainAmountText.text = "TotalPrice : ${anan} Rs "
            viewModel.delete(currentPosition)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

