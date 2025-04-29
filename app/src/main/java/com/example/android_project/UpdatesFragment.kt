package com.example.android_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Data class to represent an update item
data class Update(val title: String, val date: String, val description: String)

class UpdatesFragment : Fragment() {

    private lateinit var updatesRecyclerView: RecyclerView
    private lateinit var emptyUpdatesTextView: TextView
    private lateinit var updateAdapter: UpdateAdapter
    private val updatesList = mutableListOf<Update>() // Replace with your actual data source

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_updates, container, false)
        updatesRecyclerView = view.findViewById(R.id.updatesRecyclerView)
        emptyUpdatesTextView = view.findViewById(R.id.emptyUpdatesTextView)
        updatesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Add more sample data
        updatesList.add(Update("New Job Listings Available!", "April 15, 2025", "Exciting new job openings have been posted. Check them out now!"))
        updatesList.add(Update("Upcoming Job Fair Announcement", "April 10, 2025", "Mark your calendars for our upcoming job fair on April 25th."))
        updatesList.add(Update("Resume Builder Updated", "April 5, 2025", "The resume builder tool has been updated with new templates and features."))
        updatesList.add(Update("Coaching Session Schedule Released", "March 30, 2025", "The schedule for the upcoming job coaching sessions is now available. Please check the coaching section."))
        updatesList.add(Update("New Institute Added to Directory", "March 25, 2025", "We've added a new institute specializing in vocational training for persons with disabilities. Find their details in the Institutes section."))
        updatesList.add(Update("Tips for Job Interviews", "March 20, 2025", "Read our latest article on essential tips to ace your job interviews."))
        updatesList.add(Update("Document Upload Guide Updated", "March 15, 2025", "The guide for uploading your documents has been updated with clearer instructions."))
        updatesList.add(Update("Success Story: John Gets a New Job!", "March 10, 2025", "Read the inspiring story of John, one of our users who successfully found employment through Udyog Saarthi."))
        updatesList.add(Update("Reminder: Job Fair Next Week", "March 5, 2025", "Don't forget our job fair is happening next week! Prepare your resumes and get ready to meet potential employers."))
        updatesList.add(Update("New Accessibility Features Implemented", "February 28, 2025", "We've implemented new accessibility features to improve the app experience for all users."))
        updatesList.add(Update("Feedback Survey Open", "February 20, 2025", "We value your feedback! Please take a few minutes to complete our survey and help us improve Udyog Saarthi."))
        updatesList.add(Update("Job Coaching Workshop Announced", "February 15, 2025", "Register now for our upcoming workshop on effective job coaching strategies."))

        updateAdapter = UpdateAdapter(updatesList)
        updatesRecyclerView.adapter = updateAdapter

        updateEmptyState() // Initial check for empty state

        return view
    }

    private fun updateEmptyState() {
        if (updatesList.isEmpty()) {
            updatesRecyclerView.visibility = View.GONE
            emptyUpdatesTextView.visibility = View.VISIBLE
        } else {
            updatesRecyclerView.visibility = View.VISIBLE
            emptyUpdatesTextView.visibility = View.GONE
        }
    }

    // You might want to add a function to add new updates later
    fun addUpdate(newUpdate: Update) {
        updatesList.add(newUpdate)
        updateAdapter.notifyItemInserted(updatesList.size - 1)
        updateEmptyState()
    }
}

// Adapter for the RecyclerView
class UpdateAdapter(private val updates: List<Update>) : RecyclerView.Adapter<UpdateAdapter.UpdateViewHolder>() {

    class UpdateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.updateTitleTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.updateDateTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.updateDescriptionTextView)
        // val readMoreButton: Button = itemView.findViewById(R.id.readMoreButton) // If you decide to use it
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_update, parent, false)
        return UpdateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UpdateViewHolder, position: Int) {
        val currentUpdate = updates[position]
        holder.titleTextView.text = currentUpdate.title
        holder.dateTextView.text = currentUpdate.date
        holder.descriptionTextView.text = currentUpdate.description
        // You can add logic for the "Read More" button here if needed
    }

    override fun getItemCount() = updates.size
}