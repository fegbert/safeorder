package com.groupthree.safeorder.customviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.groupthree.safeorder.R
import com.groupthree.safeorder.database.Product

class ProductAdapter(private val data: List<Product>) :  RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.product_profile, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = data[position]
        holder.productNameView.text = item.productName
        holder.productPriceView.text = "${item.productPrice}€"
        holder.productDescriptionView.text = item.productDescription
        //button listener
    }

    override fun getItemCount(): Int = data.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameView: TextView = itemView.findViewById(R.id.p_profile_name)
        val productPriceView: TextView = itemView.findViewById(R.id.p_profile_price)
        val productDescriptionView: TextView = itemView.findViewById(R.id.p_profile_description)
        val productAddToCartButton: MaterialButton = itemView.findViewById(R.id.p_profile_addToCart_btn)

    }


}