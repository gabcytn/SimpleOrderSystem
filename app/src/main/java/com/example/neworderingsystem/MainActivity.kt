package com.example.neworderingsystem

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.math.BigDecimal
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lvListView)

        val foods = arrayListOf(
            FoodData(R.drawable.burger, "Burger", 199.99, false),
            FoodData(R.drawable.hotdog, "Hotdog", 39.99, false),
            FoodData(R.drawable.nuggets, "Nuggets", 79.99, false),
            FoodData(R.drawable.noodles, "Noodles", 49.99, false),
            FoodData(R.drawable.fries, "Fries", 99.99, false),
            FoodData(R.drawable.juice, "Juice", 29.99, false)
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
            if (orderedItems.isNotEmpty()){
                findViewById<TextView>(R.id.tvTotal).text = "Total"

                var sum = BigDecimal(0.0)
                for (i in prices){
                    sum += BigDecimal(i)
                }

                findViewById<TextView>(R.id.tvTotalAmount).text = "P${sum}"
                return@setOnClickListener
            }
            Toast.makeText(this, "No orders placed!", Toast.LENGTH_SHORT).show()
        }

    }
}