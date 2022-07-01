package com.example.codialapp.Classes

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import com.example.codialapp.Courses
import com.example.codialapp.R
import com.example.codialapp.databinding.DialogAddCoursesBinding
import com.example.codialapp.databinding.FragmentCoursesBinding
import com.example.codialapp.db.MyDBHelper

class AddCourses(
    var activity: Activity,
    var binding: FragmentCoursesBinding,
    var findNavController: NavController,
) {
    private lateinit var bindingDialog: DialogAddCoursesBinding
    lateinit var dialog: AlertDialog
    lateinit var myDBHelper: MyDBHelper
    lateinit var showCourses: ShowCourses
    var booleanAntiBag = true
    fun buildDialog() {
        bindingDialog = DialogAddCoursesBinding.inflate(activity.layoutInflater)
        val alertDialog = AlertDialog.Builder(activity)

        bindingDialog.tvCancel.setOnClickListener {
            dialog.cancel()
        }

        bindingDialog.tvSave.setOnClickListener {
            val name = bindingDialog.edtCoursesName.text.toString().trim()
            val about = bindingDialog.edtCoursesAbout.text.toString().trim()
            if (name.isNotEmpty() && about.isNotEmpty()) {
                myDBHelper = MyDBHelper(activity)
                if (myDBHelper.addCourses(Courses(name, about), activity)) {
                    showCourses = ShowCourses(activity, binding, findNavController)
                    showCourses.showCourses()
                    dialog.cancel()
                }
            }
        }

        alertDialog.setOnCancelListener {
            booleanAntiBag = true
        }

        alertDialog.setView(bindingDialog.root)
        dialog = alertDialog.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (booleanAntiBag) {
            dialog.show()
            booleanAntiBag = false
        }
    }
}