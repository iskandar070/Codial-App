package com.example.codialapp.Classes

import android.app.Activity
import androidx.navigation.NavController
import com.example.codialapp.Adapters.CoursesAdapter
import com.example.codialapp.Courses
import com.example.codialapp.Object
import com.example.codialapp.Object.courses
import com.example.codialapp.Object.navigationID
import com.example.codialapp.databinding.FragmentCoursesBinding
import com.example.codialapp.db.MyDBHelper

class ShowCourses(
    var activity: Activity,
    var binding: FragmentCoursesBinding,
    var findNavController: NavController,
) {
    lateinit var arrayListCourses: ArrayList<Courses>
    lateinit var coursesAdapter: CoursesAdapter
    lateinit var myDBHelper: MyDBHelper

    fun showCourses() {
        loadData()
        coursesAdapter = CoursesAdapter(arrayListCourses, object : CoursesAdapter.RVClickCourses {
            override fun onClick(courses: Courses) {
                com.example.codialapp.Object.courses = courses
                findNavController.navigate(Object.navigationID)
            }

        })
        binding.recyclerCourses.adapter = coursesAdapter
    }

    private fun loadData() {
        arrayListCourses = ArrayList()
        myDBHelper = MyDBHelper(activity)
        arrayListCourses = myDBHelper.showCourses()
    }
}