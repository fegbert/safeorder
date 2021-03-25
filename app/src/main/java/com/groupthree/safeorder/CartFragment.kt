package com.groupthree.safeorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.groupthree.safeorder.customviews.CartAdapter
import com.groupthree.safeorder.databinding.CartBinding
import com.groupthree.safeorder.databinding.RestaurantOverviewBinding

class CartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: CartBinding = CartBinding.inflate(inflater)
        val recyclerView = binding.cartRecyclerview
        val adapter = CartAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

       /* private val cartViewModel: CartViewModel by viewModels {

            CartViewModelFactory((application as SafeOrderApplication).cartItemRepository)
        }*/


        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.cart, container, false)
    }
}