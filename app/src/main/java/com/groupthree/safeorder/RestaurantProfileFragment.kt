package com.groupthree.safeorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.groupthree.safeorder.database.RestaurantViewModel
import com.groupthree.safeorder.database.RestaurantViewModelFactory
import com.groupthree.safeorder.database.SafeOrderDB
import com.groupthree.safeorder.databinding.RestaurantProfileBinding

class RestaurantProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RestaurantProfileBinding.inflate(inflater)

        val app = requireActivity().application
        val dataSource = SafeOrderDB.getDatabase(requireContext()).restaurantDAO()
        val viewModelFactory = RestaurantViewModelFactory(dataSource, app)
        val restaurantViewModel = ViewModelProvider(this, viewModelFactory).get(RestaurantViewModel::class.java)
        binding.lifecycleOwner = this
        //binding.restaurantViewModel = restaurantViewModel
        val id = arguments?.getInt("res_id")
        if (id != null) {
            val restaurant = restaurantViewModel.getRestaurantWithProductsByID(id)
            binding.rProfileRestaurantName.text = restaurant?.restaurant?.restaurantName
            binding.rProfileAddress.text = restaurant?.restaurant?.address.toString()

            //add products
        }
        return binding.root
    }
}