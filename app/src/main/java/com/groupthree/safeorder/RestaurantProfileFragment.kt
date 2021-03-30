package com.groupthree.safeorder

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.groupthree.safeorder.customviews.ProductAdapter
import com.groupthree.safeorder.customviews.RestaurantAdapter
import com.groupthree.safeorder.database.SafeOrderDB
import com.groupthree.safeorder.databinding.RestaurantProfileBinding
import com.groupthree.safeorder.viewmodels.RestaurantViewModel
import com.groupthree.safeorder.viewmodels.RestaurantViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantProfileFragment : Fragment() {
    private val fr = this


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RestaurantProfileBinding.inflate(inflater)

        lifecycleScope.launch(Dispatchers.IO) {
            val dataSource = SafeOrderApplication(requireContext()).restaurantRepository
            val viewModelFactory = RestaurantViewModelFactory(dataSource)
            val restaurantViewModel = ViewModelProvider(fr, viewModelFactory).get(
                RestaurantViewModel::class.java)
            binding.lifecycleOwner = fr
            binding.restaurantViewModel = restaurantViewModel
            val id = arguments?.getInt("res_id")
            val restaurant = restaurantViewModel.getRestaurantWithProductsByID(id!!)
            val products = restaurant?.products
            val recyclerView = binding.productRecyclerView
            val controller = findNavController()


            binding.topAppBarRestaurantoverview.setNavigationOnClickListener {
                controller.navigate(R.id.restaurantOverviewFragment)
            }

            fr.requireActivity().runOnUiThread {

                binding.rProfileRestaurantName.text = restaurant?.restaurant?.restaurantName
                binding.rProfileAddress.text = "${restaurant?.restaurant?.address?.street} ${restaurant?.restaurant?.address?.number} - ${restaurant?.restaurant?.address?.zip} ${restaurant?.restaurant?.address?.city}"
                binding.topAppBarRestaurantoverview.title = restaurant?.restaurant?.restaurantName
                val productAdapter = ProductAdapter(products!!)
                recyclerView.adapter = productAdapter
                recyclerView.layoutManager = LinearLayoutManager(activity)
            }
        }
        return binding.root
    }
}