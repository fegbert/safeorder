package com.groupthree.safeorder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.groupthree.safeorder.databinding.SettingsBinding
import kotlinx.android.synthetic.main.settings.*

class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = SettingsBinding.inflate(inflater)
        val controller = findNavController()


        binding.settingsTopAppBar.setOnMenuItemClickListener {
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