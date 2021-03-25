package com.groupthree.safeorder.customviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.groupthree.safeorder.CartViewModel
import com.groupthree.safeorder.R
import com.groupthree.safeorder.database.CartItem
import androidx.recyclerview.widget.ListAdapter
import com.groupthree.safeorder.database.SafeOrderDB

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    val data = listOf<CartItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cart_card, parent, false)
        return CartViewHolder(view)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = data[position]

        holder?.productQuantityView.text = item.units.toString()
    }


    override fun getItemCount(): Int = data.size

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameView: TextView = itemView.findViewById(R.id.cart_product_name)
        val productPriceView: TextView = itemView.findViewById(R.id.cart_product_price)
        val minusButtonView: MaterialButton = itemView.findViewById(R.id.cart_btn_minus)
        val plusButtonView: MaterialButton = itemView.findViewById(R.id.cart_btn_plus)
        val productQuantityView: TextView = itemView.findViewById(R.id.cart_product_quantity)



    }

}


