package com.example.android_project


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class JobFairsActivity : AppCompatActivity() {

    private lateinit var upcomingJobFairsContainer: LinearLayout
    private lateinit var pastJobFairsContainer: LinearLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_fairs)

        upcomingJobFairsContainer = findViewById(R.id.upcomingJobFairsContainer)
        pastJobFairsContainer = findViewById(R.id.pastJobFairsContainer)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupNavigation()

        loadJobFairs()
    }

    private fun setupNavigation() {
        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        val btnCoaching = findViewById<ImageButton>(R.id.btnCoaching)
        val btnJobs = findViewById<ImageButton>(R.id.btnJobs)
        val btnProfile = findViewById<ImageButton>(R.id.btnProfile)

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnCoaching.setOnClickListener {
            val intent1 = Intent(this, CoachingActivity::class.java)
            startActivity(intent1)
        }

        btnJobs.setOnClickListener {
            val intent2 = Intent(this, JobFairsActivity::class.java)
            startActivity(intent2)
        }

        btnProfile.setOnClickListener {
            val intent3 = Intent(this, ProfileActivity::class.java)
            startActivity(intent3)
        }
    }


    private fun loadJobFairs() {
        val upcomingJobFairs = listOf(
            JobFair(
                title = "Inclusive Employment Fair 2025",
                organizer = "NIEPMD & Tech Employers Association",
                date = "May 15, 2025",
                location = "Chennai, Tamil Nadu",
                imageResId = R.drawable.job_fair_1
            ),
            JobFair(
                title = "Government Sector Job Fair",
                organizer = "Ministry of Social Justice & Empowerment",
                date = "June 10, 2025",
                location = "New Delhi",
                imageResId = R.drawable.job_fair_2
            )
        )

        val pastJobFairs = listOf(
            JobFair(
                title = "IT Career Expo 2024",
                organizer = "Tech Industry Forum",
                date = "December 12, 2024",
                location = "Bangalore, Karnataka",
                imageResId = R.drawable.job_fair_3
            ),
            JobFair(
                title = "Healthcare Industry Opportunities",
                organizer = "Healthcare Alliance & NIEPMD",
                date = "November 5, 2024",
                location = "Mumbai, Maharashtra",
                imageResId = R.drawable.job_fair_4
            )
        )

        for (jobFair in upcomingJobFairs) {
            addJobFairCard(upcomingJobFairsContainer, jobFair)
        }

        for (jobFair in pastJobFairs) {
            addJobFairCard(pastJobFairsContainer, jobFair)
        }
    }

    private fun addJobFairCard(container: LinearLayout, jobFair: JobFair) {
        val cardView = LayoutInflater.from(this).inflate(R.layout.item_job_fair, container, false)

        val jobFairImage = cardView.findViewById<ImageView>(R.id.jobFairImage)
        val jobFairTitle = cardView.findViewById<TextView>(R.id.jobFairTitle)
        val jobFairOrganizer = cardView.findViewById<TextView>(R.id.jobFairOrganizer)
        val jobFairDate = cardView.findViewById<TextView>(R.id.jobFairDate)
        val jobFairLocation = cardView.findViewById<TextView>(R.id.jobFairLocation)
        val viewDetailsButton = cardView.findViewById<Button>(R.id.viewDetailsButton)

        jobFairImage.setImageResource(jobFair.imageResId)
        jobFairTitle.text = jobFair.title
        jobFairOrganizer.text = jobFair.organizer
        jobFairDate.text = jobFair.date
        jobFairLocation.text = jobFair.location

        viewDetailsButton.setOnClickListener {
            Toast.makeText(this, "Viewing details for ${jobFair.title}", Toast.LENGTH_SHORT).show()


        }

        container.addView(cardView)
    }

    data class JobFair(
        val title: String,
        val organizer: String,
        val date: String,
        val location: String,
        val imageResId: Int
    )
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}