package com.groupthree.safeorder.customviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.groupthree.safeorder.R
import com.groupthree.safeorder.database.CartItem
import androidx.recyclerview.widget.ListAdapter
import com.groupthree.safeorder.database.SafeOrderDB

class CartAdapter(private val data : List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cart_card, parent, false)
        return CartViewHolder(view)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = data[position]
        holder.productPriceView.text = item.productPrice.toString() + "€"
        holder.productNameView.text = item.productName
    }


    override fun getItemCount(): Int = data.size

    fun submitList(it: List<CartItem>) {
        if (it == data)
            return
    }


    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productNameView: TextView = itemView.findViewById(R.id.cart_product_name)
        var productPriceView: TextView = itemView.findViewById(R.id.cart_product_price)
    }

}


