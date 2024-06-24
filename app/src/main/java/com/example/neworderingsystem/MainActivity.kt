package com.example.neworderingsystem

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
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
            FoodData(R.drawable.noodles, "Noodles", 50, false),
            FoodData(R.drawable.fries, "Fries", 100, false)
        )

        val adapter = ListAdapter(this, R.layout.food_item, foods)
        listView.adapter = adapter

        listView.setOnItemClickListener{ _, view, position, _ ->
            val selectedItemCheck = view.findViewById<CheckBox>(R.id.cbFoodCheck)
            selectedItemCheck.isChecked = !selectedItemCheck.isChecked
            foods[position].check = !foods[position].check
        }

        findViewById<Button>(R.id.btnCheckout).setOnClickListener {
            val orderedItems = foods.toMutableList().filter { it.check }.map { it.name }
            val prices = foods.toMutableList().filter { it.check }.map { it.price }
            val checkedDrink = findViewById<RadioGroup>(R.id.rgRadioGroup).checkedRadioButtonId
            val drink = findViewById<RadioButton>(checkedDrink)

            if (orderedItems.isNotEmpty()){
                var sum = 0
                for (i in prices){
                    sum += i
                }

                if (drink != null){
                    sum += 20
                }
                findViewById<TextView>(R.id.tvTotal).text = "Total"
                findViewById<TextView>(R.id.tvTotalAmount).text = "P${sum}.00"
                return@setOnClickListener
            }
            findViewById<TextView>(R.id.tvTotal).text = ""
            findViewById<TextView>(R.id.tvTotalAmount).text = ""
            Toast.makeText(this, "No orders placed!", Toast.LENGTH_SHORT).show()
        }

    }
}