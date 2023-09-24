package com.example.minggu5laprak

import android.app.Activity
import android.content.Intent
import android.graphics.Color
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

            spannableClick(findViewById<TextView?>(R.id.already_account), "Log In")
            spannableClick(findViewById(R.id.terms_conditions), "Terms")
            spannableClick(findViewById(R.id.terms_conditions), "Conditions")

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == requestCode && resultCode == Activity.RESULT_OK) {
            val called = data?.getBooleanExtra("callMethod", false)
            if(called == true) {
                resetText()
            }
        }
    }

    private fun spannableClick(tv: TextView, clickString: String) {
        val spannableString = SpannableString(tv.text)
        val clickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                val des = "https://www.youtube.com/watch?v=ZsaPdoArcRs&list=RDGMEMhCgTQvcskbGUxqI4Sn2QYw&index=4"
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

    private fun resetText() {
        with(binding) {
            editTextUsername.text = null
            editTextEmail.text = null
            editTextPassword.text = null
            editTextPhone.text = null
        }
    }


}