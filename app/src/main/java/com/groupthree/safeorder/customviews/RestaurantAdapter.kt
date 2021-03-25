package com.groupthree.safeorder.customviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.groupthree.safeorder.R
import com.groupthree.safeorder.database.CartItem
import com.groupthree.safeorder.database.Restaurant

class RestaurantAdapter(private val data : List<Restaurant>) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.restaurant_card, parent, false)
        return RestaurantAdapter.RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = data[position]
        holder.restaurantNameView.text = item.restaurantName
        holder.restaurantAddressView.text = "${item.address.street} ${item.address.number} - ${item.address.zip} ${item.address.city}"
        holder.restaurantHiddenIDView.text = item.restaurantID.toString()
        holder.restaurantShowButtonView.setOnClickListener {
            val id = holder.restaurantHiddenIDView.text.toString().toInt()
            val bundleID = Bundle()
            bundleID.putInt("res_id", id)
            val nav= it.findNavController()
            nav.navigate(R.id.restaurantProfileFragment, bundleID)
        }
    }

    override fun getItemCount(): Int = data.size


    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val restaurantNameView: TextView = itemView.findViewById(R.id.rCard_res_name)
        val restaurantAddressView: TextView = itemView.findViewById(R.id.rCard_res_address)
        val restaurantShowButtonView: MaterialButton = itemView.findViewById(R.id.rCard_btnShow)
        val restaurantHiddenIDView: TextView = itemView.findViewById(R.id.rCard_hiddenID)

    }


}

