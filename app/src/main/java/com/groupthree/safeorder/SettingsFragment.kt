package com.groupthree.safeorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.groupthree.safeorder.databinding.SettingsBinding

class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = SettingsBinding.inflate(inflater)
        val controller = findNavController()

        binding.topAppBarUserprofile.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.back -> {
                    controller.navigate(R.id.userProfileFragment)
                    true
                }
                else -> false
            }
        }

        return binding.root
    }
}