package com.example.lawcompass.View

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lawcompass.R
import com.example.lawcompass.databinding.FragmentProfileBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentProfileBinding.inflate(inflater, container, false)


        sharedPreferences = requireActivity().getSharedPreferences("Users", Context.MODE_PRIVATE)


        val username = sharedPreferences.getString("username", "No Username")
        val email = sharedPreferences.getString("pass", "No Email")



        binding.profilename.text = username
        binding.profilepass.text = email



        /*binding.logoutButton.setOnClickListener {
            logoutUser()
        }*/

        return binding.root
    }

    /*private fun logoutUser() {
        val editor = sharedPreferences.edit()
        editor.clear() // Clears all saved user data
        editor.apply()

        // Redirect to Login Screen
        val intent = Intent(requireActivity(), Login::class.java)
        startActivity(intent)
        requireActivity().finish()
    }*/
}


