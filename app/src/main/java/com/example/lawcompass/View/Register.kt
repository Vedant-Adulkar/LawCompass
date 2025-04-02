package com.example.lawcompass.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lawcompass.Model.UserData
import com.example.lawcompass.R
import com.example.lawcompass.databinding.ActivityRegisterBinding
import com.google.android.gms.common.internal.Objects
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Register : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Profiles")

        binding.myregisterbtn.setOnClickListener {
            val username = binding.myregistername.text.toString()
            val pass = binding.myregisterpass.text.toString()

            if(username.isNotEmpty() && pass.isNotEmpty()){
                registerUser(username,pass)
            }
            else{
                Toast.makeText(this@Register,"Pls fill in all detail",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun registerUser(username:String,pass:String){
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(!snapshot.exists()){
                        val id = databaseReference.push().key
                        val user = UserData(id,username,pass)
                        databaseReference.child(id!!).setValue(user)
                        Toast.makeText(this@Register,"Registered Successfully",Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@Register,Login::class.java))
                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Register,"Database Error",Toast.LENGTH_SHORT).show()

                }

            }
        )

    }
}