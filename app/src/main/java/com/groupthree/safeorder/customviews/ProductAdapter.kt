package com.groupthree.safeorder.customviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.groupthree.safeorder.R
import com.groupthree.safeorder.RestaurantProfileFragment
import com.groupthree.safeorder.SafeOrderApplication
import com.groupthree.safeorder.database.*
import com.groupthree.safeorder.viewmodels.CartViewModel
import com.groupthree.safeorder.viewmodels.CartViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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
        holder.productHiddenIDView.text = item.productID.toString()
        holder.productAddToCartButton.setOnClickListener {
            val id = holder.productHiddenIDView.text.toString().toInt()
            val price = item.productPrice.split(".")[0].toInt()
            val cartItem = CartItem(productID = id, productName = item.productName, productPrice = price, units = 1, restaurantOfProductID = item.restaurantOfProductID)
            val fr = it.findFragment<RestaurantProfileFragment>()

            fr.lifecycleScope.launch(Dispatchers.IO) {
                val dataSource = SafeOrderApplication(fr.requireContext()).cartItemRepository
                val viewModelFactory = CartViewModelFactory(dataSource!!)
                val cartViewModel = ViewModelProvider(fr, viewModelFactory!!).get(CartViewModel::class.java)
                cartViewModel.insertCartItem(cartItem)
            }
            Toast.makeText(fr.requireContext(), "Gericht zum Warenkorb hinzugefügt.", Toast.LENGTH_SHORT).show()
            val nav = it.findNavController()
            nav.navigate(R.id.cartFragment)
        }
    }

    override fun getItemCount(): Int = data.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameView: TextView = itemView.findViewById(R.id.p_profile_name)
        val productPriceView: TextView = itemView.findViewById(R.id.p_profile_price)
        val productDescriptionView: TextView = itemView.findViewById(R.id.p_profile_description)
        val productAddToCartButton: MaterialButton = itemView.findViewById(R.id.p_profile_addToCart_btn)
        val productHiddenIDView : TextView = itemView.findViewById(R.id.p_profile_hiddenID)

    }


}