package com.example.android_project



import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.android_project.databinding.ActivityCoachingBinding

class CoachingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoachingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupClickListeners()
        updateProgressBars()
    }

    private fun setupClickListeners() {
        binding.btnInterviewSkills.setOnClickListener {
            showCustomToast("Opening Interview Skills module")
        }

        binding.btnWorkplaceEtiquette.setOnClickListener {
            showCustomToast("Opening Workplace Etiquette module")
        }

        binding.btnReschedule.setOnClickListener {
            showCustomToast("Opening reschedule options")
        }

        binding.btnScheduleSession.setOnClickListener {
            showCustomToast("Opening session scheduling")
        }

        binding.btnReadMore.setOnClickListener {
            showCustomToast("Opening success story details")
        }


        binding.btnHome.setOnClickListener {
            showCustomToast("Navigating to Home")
            finish()
        }

        binding.btnCoaching.setOnClickListener {
            showCustomToast("Already on Coaching page")
        }

        binding.btnJobs.setOnClickListener {
            val intent2 = Intent(this, JobFairsActivity::class.java)
            startActivity(intent2)

        }

        binding.btnProfile.setOnClickListener {
            val intent3 = Intent(this, ProfileActivity::class.java)
            startActivity(intent3)
        }


    }

    private fun updateProgressBars() {
        binding.progressBarInterviewSkills.progress = 70
        binding.progressBarResumeBuilding.progress = 45
        binding.progressBarJobResearch.progress = 30
    }

    private fun showCustomToast(message: String) {
        val inflater = LayoutInflater.from(this)
        val layout = inflater.inflate(R.layout.custom_toast, null)

        val textView = layout.findViewById<TextView>(R.id.toast_text)
        textView.text = message

        val iconView = layout.findViewById<ImageView>(R.id.toast_icon)
        iconView.setImageResource(R.drawable.ic_report_problem)

        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM, 0, 150)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
