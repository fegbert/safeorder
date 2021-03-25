package com.groupthree.safeorder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import com.groupthree.safeorder.customviews.RestaurantListAdapters
import com.groupthree.safeorder.database.Restaurant
import com.groupthree.safeorder.database.RestaurantViewModel
import com.groupthree.safeorder.database.RestaurantViewModelFactory
import com.groupthree.safeorder.database.SafeOrderDB
import com.groupthree.safeorder.databinding.RestaurantOverviewBinding

class RestaurantOverviewFragment : Fragment() {

    private var listView : ListView? = null
    private var restaurantListAdapters : RestaurantListAdapters? = null
    private var resList : List<Restaurant>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : RestaurantOverviewBinding = RestaurantOverviewBinding.inflate(inflater)

        val app = requireActivity().application
        val dataSource = SafeOrderDB.getDatabase(requireContext()).restaurantDAO()
        val viewModelFactory = RestaurantViewModelFactory(dataSource, app)
        val restaurantViewModel = ViewModelProvider(this, viewModelFactory).get(RestaurantViewModel::class.java)
        binding.lifecycleOwner = this
        binding.restaurantViewModel = restaurantViewModel
        resList = restaurantViewModel.getRestaurants()
        listView = binding.restaurantOverviewList
        Log.w("CheckList", "${resList?.size}")
        if (resList != null) {
            restaurantListAdapters = RestaurantListAdapters(requireContext(), resList!!)
            listView?.adapter = restaurantListAdapters
        }
        return binding.root

        /*val binding = RestaurantOverviewBinding.inflate(inflater)
        val controller = findNavController()

        binding.testBTN.setOnClickListener {
            when(it.id){
                R.id.testBTN -> {
                    controller.navigate(R.id.cartFragment)
                    true
                }
                else -> false
            }
        }

return binding.root

    */
    }

}