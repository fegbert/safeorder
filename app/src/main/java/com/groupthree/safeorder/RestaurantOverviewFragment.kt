package com.groupthree.safeorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.groupthree.safeorder.databinding.RestaurantOverviewBinding
import com.groupthree.safeorder.databinding.SettingsBinding

class RestaurantOverviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.restaurant_overview, container, false)

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