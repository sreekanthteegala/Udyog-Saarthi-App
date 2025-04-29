package com.example.android_project




import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.chip.Chip

class JobListingsActivity : AppCompatActivity() {

    private lateinit var jobsContainer: LinearLayout
    private lateinit var progressBar: View
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar


    private val jobsList = listOf(
        JobListing(
            "Software Developer",
            "TechInnovations Pvt Ltd",
            "Bangalore, Karnataka",
            "₹40,000/month",
            "Full-time",
            true,
            "We are looking for a passionate Software Developer to design, develop and install software solutions. The successful candidate will develop high-quality software design and architecture.",
            "3 days ago"
        ),
        JobListing(
            "Customer Support Executive",
            "GlobalServe Solutions",
            "Mumbai, Maharashtra",
            "₹25,000/month",
            "Part-time",
            true,
            "Join our team as a Customer Support Executive to handle customer queries, provide product information and resolve complaints through multiple channels.",
            "1 day ago"
        ),
        JobListing(
            "Content Writer",
            "CreativeMinds Media",
            "Remote",
            "₹35,000/month",
            "Contract",
            true,
            "We're hiring a talented Content Writer to create engaging content for our clients across various industries including technology, healthcare and education.",
            "5 days ago"
        ),
        JobListing(
            "Administrative Assistant",
            "Corporate Services Ltd",
            "Delhi NCR",
            "₹28,000/month",
            "Full-time",
            true,
            "Looking for an organized Administrative Assistant to support our office operations, manage schedules, handle correspondence and maintain filing systems.",
            "2 days ago"
        ),
        JobListing(
            "Graphic Designer",
            "DesignHub Studios",
            "Hyderabad, Telangana",
            "₹32,000/month",
            "Full-time",
            false,
            "Join our creative team to design visual concepts for websites, social media, print materials and brand identities using design software.",
            "Just now"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_listings)

        jobsContainer = findViewById(R.id.jobsContainer)
        progressBar = findViewById(R.id.progressBar)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupChips()
        setupNavigation()

        showProgress(true)

        android.os.Handler().postDelayed({

            loadJobs()
            showProgress(false)
        }, 1500)
    }

    private fun setupChips() {
        findViewById<Chip>(R.id.chipLocation).setOnClickListener { showFilterToast("Location") }
        findViewById<Chip>(R.id.chipCategory).setOnClickListener { showFilterToast("Category") }
        findViewById<Chip>(R.id.chipSalary).setOnClickListener { showFilterToast("Salary") }
        findViewById<Chip>(R.id.chipExperience).setOnClickListener { showFilterToast("Experience") }
        findViewById<Chip>(R.id.chipAccessibility).setOnClickListener { showFilterToast("Accessibility") }
    }

    private fun loadJobs() {
        // Clear any existing job views
        jobsContainer.removeAllViews()

        // Add each job to the container
        jobsList.forEach { job ->
            val jobView = LayoutInflater.from(this).inflate(R.layout.item_job, jobsContainer, false)

            // Set job details
            jobView.findViewById<TextView>(R.id.jobTitleTextView).text = job.title
            jobView.findViewById<TextView>(R.id.companyTextView).text = job.company
            jobView.findViewById<TextView>(R.id.locationTextView).text = job.location
            jobView.findViewById<TextView>(R.id.salaryTextView).text = job.salary
            jobView.findViewById<TextView>(R.id.jobTypeTextView).text = job.type
            jobView.findViewById<TextView>(R.id.descriptionTextView).text = job.description
            jobView.findViewById<TextView>(R.id.postedDateTextView).text = "Posted ${job.postedDate}"

            // Configure accessibility badge visibility
            val accessibilityBadge = jobView.findViewById<TextView>(R.id.accessibilityTextView)
            accessibilityBadge.visibility = if (job.isAccessible) View.VISIBLE else View.GONE

            jobView.findViewById<View>(R.id.saveJobButton).setOnClickListener {
                showCustomToast("Job saved to favorites")
            }

            jobView.findViewById<View>(R.id.applyButton).setOnClickListener {
                showCustomToast("Application started for ${job.title}")
            }

            jobsContainer.addView(jobView)
        }
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

    private fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        jobsContainer.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun showFilterToast(filterName: String) {
        showCustomToast("Filtering by $filterName")
    }

    private fun showCustomToast(message: String) {
        val layout = layoutInflater.inflate(R.layout.custom_toast_job, null)
        val text = layout.findViewById<TextView>(R.id.toastMessage)
        text.text = message

        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 150)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

    data class JobListing(
        val title: String,
        val company: String,
        val location: String,
        val salary: String,
        val type: String,
        val isAccessible: Boolean,
        val description: String,
        val postedDate: String
    )
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}