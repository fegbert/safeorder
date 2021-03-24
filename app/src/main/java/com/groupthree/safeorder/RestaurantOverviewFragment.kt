package com.groupthree.safeorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.groupthree.safeorder.customviews.RestaurantListAdapters
import com.groupthree.safeorder.database.Restaurant
import com.groupthree.safeorder.database.RestaurantViewModel
import com.groupthree.safeorder.database.RestaurantViewModelFactory
import com.groupthree.safeorder.database.SafeOrderDB
import com.groupthree.safeorder.databinding.RestaurantOverviewBinding
import com.groupthree.safeorder.databinding.SettingsBinding
import kotlinx.coroutines.launch

class RestaurantOverviewFragment : Fragment() {

    private var listView : ListView? = null
    private var restaurantListAdapters : RestaurantListAdapters? = null
    private var resList : List<Restaurant>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : RestaurantOverviewBinding = RestaurantOverviewBinding.inflate(inflater)

     /*   val app = requireActivity().application
        val dataSource = SafeOrderDB.getDatabase(requireContext()).restaurantDAO()
        val viewModelFactory = RestaurantViewModelFactory(dataSource, app)
        val restaurantViewModel = ViewModelProvider(this, viewModelFactory).get(RestaurantViewModel::class.java)
        binding.lifecycleOwner = this
        binding.restaurantViewModel = restaurantViewModel
        resList = restaurantViewModel.getRestaurants()
        listView = binding.restaurantOverviewList
        if (resList != null) {
            restaurantListAdapters = RestaurantListAdapters(requireContext(), resList!!)
            listView?.adapter = restaurantListAdapters
        }*/
        return binding.root


    }

}