package com.example.codialapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codialapp.Classes.ShowMentors
import com.example.codialapp.databinding.FragmentMentorsBinding

class MentorsFragment : Fragment() {
    lateinit var binding: FragmentMentorsBinding
    lateinit var showMentors: ShowMentors
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        loadData()
        showWindow()
        setOnClick()

        return binding.root
    }

    private fun setOnClick() {
        binding.lyAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mentorsFragment_to_addMentorsFragment)
        }
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadData() {
        binding = FragmentMentorsBinding.inflate(layoutInflater)
        showMentors = ShowMentors(requireActivity(), binding)
    }

    private fun showWindow() {
        binding.tvCoursesName.text = Object.courses.name
        showMentors.showMentors()
    }

}