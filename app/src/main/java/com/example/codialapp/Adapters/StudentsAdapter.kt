package com.example.codialapp.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codialapp.Students
import com.example.codialapp.databinding.ItemStudentsBinding

class StudentsAdapter(val context: Context, private val arrayList: ArrayList<Students>, var rvClickStudents: RVClickStudents) :
    RecyclerView.Adapter<StudentsAdapter.VH>() {

    inner class VH(private var itemRV: ItemStudentsBinding) : RecyclerView.ViewHolder(itemRV.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(students: Students) {
            itemRV.tvName.text = "${students.name} ${students.surname}"
            itemRV.tvDate.text = students.day
            itemRV.cardEdit.setOnClickListener {
                rvClickStudents.editStudents(students)
            }
            itemRV.cardDelete.setOnClickListener {
                rvClickStudents.deleteStudents(students)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemStudentsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(arrayList[position])
    }

    override fun getItemCount(): Int = arrayList.size

    interface RVClickStudents{
        fun editStudents(students: Students) {

        }
        fun deleteStudents(students: Students) {

        }
    }

}