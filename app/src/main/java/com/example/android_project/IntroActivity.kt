package com.example.android_project

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android_project.R


class IntroActivity : AppCompatActivity() {
    private lateinit var getStartedBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        getStartedBtn = findViewById(R.id.btn)
        getStartedBtn.setOnClickListener {
            val sharedPref: SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("firstTime", false)
                apply()
            }

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
