package com.example.android_project
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.android_project.R



class ComplainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_complain, container, false)
}
