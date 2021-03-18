package com.groupthree.safeorder

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.groupthree.safeorder.databinding.UserProfileBinding

class UserProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = UserProfileBinding.inflate(inflater)
        val controller = findNavController()

        binding.topAppBarUserprofile.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings_user -> {
                    controller.navigate(R.id.settingsFragment)
                    true
                }
                else -> false
            }
        }

        return binding.root
    }
}