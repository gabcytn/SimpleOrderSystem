package com.example.neworderingsystem

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class ListAdapter (context: Activity, private val resource: Int,  private val foodList: ArrayList<FoodData>) :
        ArrayAdapter<FoodData>(context, R.layout.food_item, foodList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val item = foodList[position]

        val foodImage = view.findViewById<ImageView>(R.id.ivFoodImage)
        val foodName = view.findViewById<TextView>(R.id.tvFoodName)
        val foodPrice = view.findViewById<TextView>(R.id.tvFoodPrice)
        val foodCheck = view.findViewById<CheckBox>(R.id.cbFoodCheck)

        foodImage.setImageResource(item.image)
        foodName.text = item.name
        foodPrice.text = "P${item.price.toDouble()}"
        foodCheck.isChecked = item.check

        return view
    }
}