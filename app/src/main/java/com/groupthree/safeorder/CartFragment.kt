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
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private var recyclerView : RecyclerView? = null
    private var cartAdapter : CartAdapter? = null
    private var resList : List<CartItem>? = null
    private val fragment = this
    private var dataSource : CartItemRepository? = null
    private var viewModelFactory : CartViewModelFactory? = null
    private var cartViewModel : CartViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: CartBinding = CartBinding.inflate(inflater)

        val getData = lifecycleScope.launch(Dispatchers.IO) {
            dataSource = SafeOrderApplication(requireContext()).cartItemRepository
            viewModelFactory = CartViewModelFactory(dataSource!!)
            cartViewModel = ViewModelProvider(fragment, viewModelFactory!!).get(CartViewModel::class.java)
            resList = cartViewModel!!.allCartItems
            recyclerView = binding.cartRecyclerview
            var total = resList?.sumBy { it.productPrice }

            fragment.requireActivity().runOnUiThread {
                cartAdapter = CartAdapter(resList!!)
                recyclerView!!.adapter = cartAdapter
                recyclerView!!.layoutManager = LinearLayoutManager(activity)
                binding.cartTotalPrice.text = total.toString() + "€"
            }

            binding.cartOrderBtn.setOnClickListener {
                cartViewModel!!.clearCart()
            }

        }

        return binding.root

    }

}


