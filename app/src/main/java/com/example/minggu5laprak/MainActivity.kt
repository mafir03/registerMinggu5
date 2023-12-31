package com.example.minggu5laprak

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.example.minggu5laprak.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var s1: SpannableString
    lateinit var s2: SpannableString
    var isSpanClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            spannableClick(alreadyAccount, "Log In")
            spannableClick(termsConditions, "Terms")
            spannableClick(termsConditions, "Conditions")

            register.setOnClickListener {
                if(editTextUsername.text.isEmpty() || editTextEmail.text.isEmpty() ||
                    editTextPhone.text.isEmpty() || editTextPassword.text.isEmpty()) {
                    Snackbar.make(binding.root, "Mohon isi bagan yang kosong", Snackbar.LENGTH_SHORT).setAnchorView(alreadyAccount).show()
                } else
                {
                    if(checkBox.isChecked) {
                        val intentToSecondActivity =
                            Intent(this@MainActivity, SecondActivity::class.java)
                        intentToSecondActivity.putExtra("username", editTextUsername.text.toString())
                        intentToSecondActivity.putExtra("email", editTextEmail.text.toString())
                        intentToSecondActivity.putExtra("phone", editTextPhone.text.toString())
                        startActivity(intentToSecondActivity)
                    } else {
                        Snackbar.make(binding.root, "You do not agree with our Terms and Conditions", Snackbar.LENGTH_SHORT).setAnchorView(alreadyAccount).show()
                    }
                }
            }
        }
    }


    private fun spannableClick(tv: TextView, clickString: String) {
        val spannableString = SpannableString(tv.text)
        val clickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
            }
        }
        val int1 = tv.text.toString().indexOf(clickString)
        val int2 = int1 + clickString.length
        spannableString.setSpan(clickableSpan, int1, int2, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)

        tv.text = spannableString
        tv.movementMethod = LinkMovementMethod.getInstance()

    }

}