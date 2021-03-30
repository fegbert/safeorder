package com.groupthree.safeorder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.groupthree.safeorder.customviews.RestaurantAdapter
import com.groupthree.safeorder.database.*
import com.groupthree.safeorder.databinding.RestaurantOverviewBinding
import com.groupthree.safeorder.viewmodels.RestaurantViewModel
import com.groupthree.safeorder.viewmodels.RestaurantViewModelFactory
import kotlinx.android.synthetic.main.restaurant_card.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class RestaurantOverviewFragment : Fragment() {

    private var recyclerView : RecyclerView? = null
    private var restaurantListAdapters : RestaurantAdapter? = null
    private var resList : List<Restaurant>? = null
    private val fr = this
    private var dataSource : RestaurantRepository? = null
    private var viewModelFactory : RestaurantViewModelFactory? = null
    private var restaurantViewModel : RestaurantViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : RestaurantOverviewBinding = RestaurantOverviewBinding.inflate(inflater)

        lifecycleScope.launch(Dispatchers.IO) {
            dataSource = SafeOrderApplication(requireContext()).restaurantRepository
            viewModelFactory = RestaurantViewModelFactory(dataSource!!)
            restaurantViewModel = ViewModelProvider(fr, viewModelFactory!!).get(RestaurantViewModel::class.java)
            binding.lifecycleOwner = fr
            resList = restaurantViewModel!!.allRestaurants
            recyclerView = binding.restaurantOverviewRecyclerview

            fr.requireActivity().runOnUiThread {
                restaurantListAdapters = RestaurantAdapter(resList!!)
                recyclerView!!.adapter = restaurantListAdapters
                recyclerView!!.layoutManager = LinearLayoutManager(activity)
            }
        }

        return binding.root

    }

}