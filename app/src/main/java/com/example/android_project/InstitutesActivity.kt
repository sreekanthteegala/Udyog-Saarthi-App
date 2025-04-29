package com.example.android_project

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class InstitutesActivity : AppCompatActivity() {

    private lateinit var institutesContainer: LinearLayout
    private lateinit var searchEditText: EditText
    private lateinit var allInstitutes: List<Institute>
    private lateinit var displayedInstitutes: MutableList<Institute>
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institutes)

        institutesContainer = findViewById(R.id.institutesContainer)
        searchEditText = findViewById(R.id.searchEditText)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupNavigation()

        initializeInstitutesData()

        displayedInstitutes = allInstitutes.toMutableList()
        displayInstitutes()

        setupSearch()
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
            // Navigate to profile
            val intent3 = Intent(this, ProfileActivity::class.java)
            startActivity(intent3)
        }
    }

    private fun initializeInstitutesData() {
        allInstitutes = listOf(
            Institute(
                name = "National Institute for Empowerment of Persons with Multiple Disabilities",
                shortName = "NIEPMD",
                location = "Chennai, Tamil Nadu",
                type = "Government Institute",
                logoResId = R.drawable.ic_institute_govt,
                rating = 4.5f
            ),
            Institute(
                name = "Indian Institute of Technology",
                shortName = "IIT Madras",
                location = "Chennai, Tamil Nadu",
                type = "Technical Institute",
                logoResId = R.drawable.ic_institute_tech,
                rating = 5.0f
            ),
            Institute(
                name = "All India Institute of Medical Sciences",
                shortName = "AIIMS",
                location = "New Delhi",
                type = "Medical Institute",
                logoResId = R.drawable.ic_institute_medical,
                rating = 4.8f
            ),
            Institute(
                name = "National Institute of Mental Health and Neuro Sciences",
                shortName = "NIMHANS",
                location = "Bangalore, Karnataka",
                type = "Healthcare Institute",
                logoResId = R.drawable.ic_institute_healthcare,
                rating = 4.2f
            ),
            Institute(
                name = "Indian Institute of Management",
                shortName = "IIM Ahmedabad",
                location = "Ahmedabad, Gujarat",
                type = "Management Institute",
                logoResId = R.drawable.ic_institute_management,
                rating = 4.7f
            )
        )
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterInstitutes(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterInstitutes(query: String) {
        val filteredList = if (query.isEmpty()) {
            allInstitutes.toMutableList()
        } else {
            allInstitutes.filter { institute ->
                institute.name.contains(query, ignoreCase = true) ||
                        institute.shortName.contains(query, ignoreCase = true) ||
                        institute.location.contains(query, ignoreCase = true) ||
                        institute.type.contains(query, ignoreCase = true)
            }.toMutableList()
        }

        displayedInstitutes = filteredList
        displayInstitutes()
    }

    private fun displayInstitutes() {
        institutesContainer.removeAllViews()

        for (institute in displayedInstitutes) {
            val instituteView = LayoutInflater.from(this)
                .inflate(R.layout.item_institute, institutesContainer, false)

            // Set institute data to views
            val instituteLogo = instituteView.findViewById<ImageView>(R.id.instituteLogo)
            val instituteName = instituteView.findViewById<TextView>(R.id.instituteName)
            val instituteLocation = instituteView.findViewById<TextView>(R.id.instituteLocation)
            val instituteType = instituteView.findViewById<TextView>(R.id.instituteType)
            val instituteRating = instituteView.findViewById<RatingBar>(R.id.instituteRating)

            instituteLogo.setImageResource(institute.logoResId)
            instituteName.text = institute.shortName
            instituteLocation.text = institute.location
            instituteType.text = institute.type
            instituteRating.rating = institute.rating

            // Set up rating bar change listener
            instituteRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if (fromUser) {
                    // Update the rating in the institute object
                    institute.rating = rating
                    Toast.makeText(
                        this,
                        "Rated ${institute.shortName}: $rating stars",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Here you would typically save the rating to a database or shared preferences
                    saveRating(institute.shortName, rating)
                }
            }

            // Set click listener for the card
            instituteView.setOnClickListener {
                showInstituteDetails(institute)
            }

            institutesContainer.addView(instituteView)
        }
    }

    private fun saveRating(instituteShortName: String, rating: Float) {
        val sharedPreferences = getSharedPreferences("InstituteRatings", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat(instituteShortName, rating)
        editor.apply()
    }

    private fun loadRating(instituteShortName: String): Float {
        val sharedPreferences = getSharedPreferences("InstituteRatings", MODE_PRIVATE)
        return sharedPreferences.getFloat(instituteShortName, 0f)
    }

    private fun showInstituteDetails(institute: Institute) {
        Toast.makeText(this, "Selected: ${institute.name}", Toast.LENGTH_SHORT).show()
    }

    data class Institute(
        val name: String,
        val shortName: String,
        val location: String,
        val type: String,
        val logoResId: Int,
        var rating: Float = 0f
    )
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}