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

    val fr = this

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = UserProfileBinding.inflate(inflater)
        val controller = findNavController()
        return binding.root
    }
}


/*  binding.userProfileTopAppBar.setOnMenuItemClickListener {
      FirebaseAuth.getInstance().signOut()

      val intent = Intent(requireContext(), LoginActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      //requireActivity().startActivity(intent)
      fr.requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))*/


//  }


/* R.id.back -> { //R.id.user_profile_settings -> {
     controller.navigate(R.id.settingsFragment)
     //controller.navigate(R.id.mapsFragment)
     //FirebaseAuth.getInstance().signOut()
     //requireActivity().startActivity(Intent(this.context, LoginActivity::class.java))
     true
 }*/


//}