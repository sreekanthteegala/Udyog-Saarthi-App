package com.example.android_project




import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android_project.*

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // six buttons
        view.findViewById<Button>(R.id.btnCoaching).setOnClickListener {
            startActivity(Intent(activity, CoachingActivity::class.java))
            Toast.makeText(activity, "Coaching", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btnJobListings).setOnClickListener {
            startActivity(Intent(activity, JobListingsActivity::class.java))
            Toast.makeText(activity, "Job Listings", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btnInstitutes).setOnClickListener {
            startActivity(Intent(activity, InstitutesActivity::class.java))
            Toast.makeText(activity, "Institutes", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btnResumeBuilder).setOnClickListener {
            startActivity(Intent(activity, ResumeBuilderActivity::class.java))
            Toast.makeText(activity, "Resume Builder", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btnJobFairs).setOnClickListener {
            startActivity(Intent(activity, JobFairsActivity::class.java))
            Toast.makeText(activity, "Job Fairs", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btnDocumentUpload).setOnClickListener {
            startActivity(Intent(activity, DocumentUploadActivity::class.java))
            Toast.makeText(activity, "Document Upload", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
