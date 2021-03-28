package com.groupthree.safeorder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.groupthree.safeorder.customviews.CartAdapter
import com.groupthree.safeorder.databinding.CartBinding
import com.groupthree.safeorder.databinding.RestaurantOverviewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Observer
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.groupthree.safeorder.database.*
import com.groupthree.safeorder.databinding.RestaurantProfileBinding
import com.groupthree.safeorder.viewmodels.CartViewModel
import com.groupthree.safeorder.viewmodels.CartViewModelFactory
import com.groupthree.safeorder.viewmodels.RestaurantViewModel
import com.groupthree.safeorder.viewmodels.RestaurantViewModelFactory
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private var recyclerView : RecyclerView? = null
    private var cartAdapter : CartAdapter? = null
    private var cartItemList : List<CartItem>? = null
    private val fragment = this
    private var cartDataSource : CartItemRepository? = null
    private var cartViewModelFactory : CartViewModelFactory? = null
    private var cartViewModel : CartViewModel? = null
    private var restaurant : Restaurant? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: CartBinding = CartBinding.inflate(inflater)

        lifecycleScope.launch(Dispatchers.IO) {
            cartDataSource = SafeOrderApplication(requireContext()).cartItemRepository
            cartViewModelFactory = CartViewModelFactory(cartDataSource!!)
            cartViewModel = ViewModelProvider(fragment, cartViewModelFactory!!).get(CartViewModel::class.java)
            cartItemList = cartViewModel!!.allCartItems

            val restaurantDataSource = SafeOrderApplication(requireContext()).restaurantRepository
            val restaurantViewModelFactory = RestaurantViewModelFactory(restaurantDataSource)
            val restaurantViewModel = ViewModelProvider(fragment, restaurantViewModelFactory).get(RestaurantViewModel::class.java)
            if (!cartItemList.isNullOrEmpty()) {
                restaurant = restaurantViewModel.getRestaurantByID(cartItemList!![0].restaurantOfProductID)
            }

            recyclerView = binding.cartRecyclerview
            var total = cartItemList?.sumBy { it.productPrice }

            fragment.requireActivity().runOnUiThread {
                cartAdapter = CartAdapter(cartItemList!!)
                recyclerView!!.adapter = cartAdapter
                recyclerView!!.layoutManager = LinearLayoutManager(activity)
                binding.cartTotalPrice.text = total.toString() + " €"

                if (restaurant != null) {
                    binding.cartRestaurantName.text = restaurant?.restaurantName
                }
            }

            binding.cartOrderBtn.setOnClickListener {
                cartViewModel!!.clearCart()
            }

        }

        return binding.root

    }

}


