package com.groupthree.safeorder

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.groupthree.safeorder.databinding.UserProfileBinding
import kotlinx.android.synthetic.main.user_profile.*

class UserProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = UserProfileBinding.inflate(inflater)
        val controller = findNavController()


        binding.userProfileTopAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.back -> { //R.id.user_profile_settings -> {
                    controller.navigate(R.id.settingsFragment)
                    //controller.navigate(R.id.mapsFragment)
                    //FirebaseAuth.getInstance().signOut()
                    //requireActivity().startActivity(Intent(this.context, LoginActivity::class.java))
                    true
                }
                else -> false
            }
        }

        //val buttonSignOut : ImageButton? = settings_layout.findViewById(R.id.settings_logout)


        /*settings_logout?.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            //startActivity(Intent(this.context, LoginActivity::class.java))
            //requireActivity().startActivity(Intent(getActivity(), LoginActivity::class.java))
            ////Toast.makeText(this.context, "Signed Out", Toast.LENGTH_LONG).show()
            //requireActivity().
            ////startActivity(Intent(this.context, LoginActivity::class.java))
            Toast.makeText(this.context, "Signed Out", Toast.LENGTH_LONG).show()
            val intent = Intent(this.context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            requireActivity().startActivity(intent)
        }*/

        /*
        settings_logout.setOnClickListener {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(activity, "Signed Out", Toast.LENGTH_LONG).show()
                val intent = Intent(activity, LoginActivity::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }*/
/*
        val root = inflater.inflate(R.layout.user_profile, container, false)
        val btnUbicacion = root.findViewById<View>(R.id.settings_logout) as Button
        btnUbicacion?.setOnClickListener{
            //requireActivity().findNavController(R.id.settings_logout).navigate(R.id.action_settingsFragment_to_userProfileFragment)
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this.context, "Signed Out", Toast.LENGTH_LONG).show()
            val intent = Intent(this.context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            requireActivity().startActivity(intent)
        }

        val buttonSignOut : ImageButton? = root.findViewById(R.id.settings_logout)
        if (buttonSignOut != null) {
            buttonSignOut.setOnClickListener{
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this.context, "Signed Out", Toast.LENGTH_LONG).show()
                startActivity(Intent(this.context, LoginActivity::class.java))
            }
        }*/

        return binding.root
    }
}