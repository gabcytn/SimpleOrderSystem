package com.example.neworderingsystem

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lvListView)

        val foods = arrayListOf(
            FoodData(R.drawable.burger, "Burger", 150, false),
            FoodData(R.drawable.chicken, "Chicken", 100, false),
            FoodData(R.drawable.hotdog, "Hotdog", 40, false),
            FoodData(R.drawable.nuggets, "Nuggets", 80, false),
            FoodData(R.drawable.noodles, "Noodles", 70, false)
        )

        val adapter = ListAdapter(this, R.layout.food_item, foods)
        listView.adapter = adapter

        listView.setOnItemClickListener{ _, view, position, _ ->
            val selectedItemCheck = view.findViewById<CheckBox>(R.id.cbFoodCheck)
            selectedItemCheck.isChecked = !selectedItemCheck.isChecked
            foods[position].check = !foods[position].check
        }

        val drinksSize = arrayOf("Small", "Medium", "Large")
        val spinner = findViewById<Spinner>(R.id.spSpinner)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, drinksSize)

        spinner.adapter = arrayAdapter
        var spinnerChoice = "Small"
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerChoice = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        findViewById<Button>(R.id.btnCheckout).setOnClickListener {
            val orderedItems = foods.toMutableList().filter { it.check }.map { it.name }
            val prices = foods.toMutableList().filter { it.check }.map { it.price }
            val checkedDrink = findViewById<RadioGroup>(R.id.rgRadioGroup).checkedRadioButtonId
            val drink = findViewById<RadioButton>(checkedDrink)

            var msg = ""
            if (drink == null){
                msg = "NO DRINK SELECTED"
            } else {
                msg = drink.text.toString()
            }

            if (orderedItems.isNotEmpty() || drink != null){
                var sum = 0
                for (i in prices){
                    sum += i
                }

                if (drink != null){
                    Log.d("MainActivity", spinnerChoice)
                    when(spinnerChoice){
                        "Small" -> sum += 20
                        "Medium" -> sum += 40
                        "Large" -> sum += 60
                    }
                }

                Log.d("MainActivity", msg)
                findViewById<TextView>(R.id.tvTotal).text = "Total"
                findViewById<TextView>(R.id.tvTotalAmount).text = "P${sum}.00"
                return@setOnClickListener
            } else {
                findViewById<TextView>(R.id.tvTotal).text = ""
                findViewById<TextView>(R.id.tvTotalAmount).text = ""
                Toast.makeText(this, "No orders placed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}