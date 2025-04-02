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
import com.example.lawcompass.databinding.ActivityLoginBinding
import com.example.lawcompass.databinding.ActivityRegisterBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Profiles")
        binding.myloginbtn.setOnClickListener {
            val username = binding.myloginrname.text.toString()
            val pass = binding.myloginpass.text.toString()
            if(username.isNotEmpty() && pass.isNotEmpty()){
                loginUser(username,pass)
            }
            else{
                Toast.makeText(this@Login,"Pls fill in all detail", Toast.LENGTH_SHORT).show()
            }
        }
        binding.mylodintxt.setOnClickListener {
            startActivity(Intent(this@Login,Register::class.java))

        }

    }
    private fun loginUser(username:String,pass:String){
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(
            object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(user in snapshot.children){
                            val userdata = user.getValue(UserData::class.java)
                            if(userdata!=null && userdata.pass == pass){
                                Toast.makeText(this@Login,"Logged in successfully", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Login,MainActivity::class.java))
                                finish()
                            }
                        }
                    }
                    else{
                        Toast.makeText(this@Login,"Logged in successfully", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Login,"DataBase Error", Toast.LENGTH_SHORT).show()
                }


            }
        )

    }
}