package com.example.codialapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.codialapp.databinding.FragmentAddEditStudentsBinding
import com.example.codialapp.db.MyDBHelper
import java.time.LocalDate

class AddEditStudentFragment : Fragment() {
    lateinit var myDBHelper: MyDBHelper
    lateinit var binding: FragmentAddEditStudentsBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        loadData()
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.imageSave.setOnClickListener {
            if (Object.editStudent) {
                editStudents()
            } else {
                addStudents()
            }
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addStudents() {
        val name = binding.edtStudentName.text.toString().trim()
        val surname = binding.edtStudentSurname.text.toString().trim()
        val number = binding.edtStudentNumber.text.toString().trim()
        val date = LocalDate.now().toString()
        val students = Students(name, surname, number, date, Object.groups)

        if (name.isNotEmpty() && surname.isNotEmpty() && number.isNotEmpty() && date.isNotEmpty()) {
            myDBHelper.addStudents(students , requireActivity())
            binding.edtStudentName.text!!.clear()
            binding.edtStudentSurname.text!!.clear()
            binding.edtStudentNumber.text!!.clear()
        }
    }

    private fun editStudents() {
        val name = binding.edtStudentName.text.toString().trim()
        val surname = binding.edtStudentSurname.text.toString().trim()
        val number = binding.edtStudentNumber.text.toString().trim()
        val students = Students(Object.students.id ,name, surname, number, Object.students.day, Object.groups)
        myDBHelper.updateStudents(students , requireActivity())
        findNavController().popBackStack()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        binding = FragmentAddEditStudentsBinding.inflate(layoutInflater)
        myDBHelper = MyDBHelper(requireActivity())
        if (Object.editStudent) {
            binding.tvAllCourses.text = "Talaba o'zgartirish"
            binding.imageSave.setImageResource(R.drawable.ic_baseline_edit_24)
            binding.edtStudentName.setText(Object.students.name)
            binding.edtStudentSurname.setText(Object.students.surname)
            binding.edtStudentNumber.setText(Object.students.number)
        }
    }

}