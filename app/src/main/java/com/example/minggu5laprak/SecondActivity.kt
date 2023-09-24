package com.example.minggu5laprak

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.minggu5laprak.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    lateinit var tv_username: TextView
    lateinit var tv_email: TextView
    lateinit var tv_password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            if(intent.extras?.getString("username") == "") {

            }
            welcomeUser.text = "Welcome ${intent.extras?.getString("username") ?: "Username"}"
            welcomeEmail.text = "Your ${intent.extras?.getString("email") ?: "email"} has been activated"
            welcomePhoneNum.text = "Your ${intent.extras?.getString("phone") ?: "phone"} has been registered"

            logout.setOnClickListener {
                val intentToFirstActivity =
                    Intent(this@SecondActivity, MainActivity::class.java)
                finish()
            }
        }

    }
}