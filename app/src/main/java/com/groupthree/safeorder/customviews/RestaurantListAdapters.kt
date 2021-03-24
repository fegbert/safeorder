package com.groupthree.safeorder.customviews

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.groupthree.safeorder.R
import com.groupthree.safeorder.database.Restaurant

class RestaurantListAdapters(var context : Context, var resList : List<Restaurant>) : BaseAdapter() {

    override fun getCount(): Int {
        return resList.size
    }

    override fun getItem(position: Int): Any {
        return resList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.restaurant_card, null)

        var rName : TextView = view.findViewById(R.id.rCard_res_name)
        var rAddress : TextView = view.findViewById(R.id.rCard_res_address)

        var restaurant : Restaurant = resList[position]

        rName.text = restaurant.restaurantName
        rAddress.text = restaurant.address.toString()



        return view!!
    }
}