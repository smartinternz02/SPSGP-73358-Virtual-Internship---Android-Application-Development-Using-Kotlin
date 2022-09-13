package com.example.mvvmstudy.UI

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialog
import com.example.mvvmstudy.R
import com.example.mvvmstudy.databinding.GroceryDialogBinding
import com.example.mvvmstudy.db.Entity.GroceryItems

class ItemDialog(context: Context, var dialogListener: DialogListener) : AppCompatDialog(context) {

    private lateinit var binding: GroceryDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = GroceryDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.saveText.setOnClickListener {
            val name = binding.itemEditText.text.toString()
            val amount = binding.amountEditText.text.toString().toInt()
            val price = binding.priceEditText.text.toString().toInt()

            if (name.isEmpty()) {
                Toast.makeText(context, "Enter item name", Toast.LENGTH_SHORT).show()
            }
            val item = GroceryItems(name,amount,price)
            dialogListener.onAddButtonClicked(item)
            dismiss()
        }

        binding.cancelText.setOnClickListener {
            cancel()
        }

    }



}

