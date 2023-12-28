package com.example.employeelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeDetailAdapter(private var details: List<Employee>, context: Context) :
    RecyclerView.Adapter<EmployeeDetailAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextview: TextView = itemView.findViewById(R.id.idName)
        val addressTextview: TextView = itemView.findViewById(R.id.idAddress)
        val genderView: TextView = itemView.findViewById(R.id.idGender)
        val department: TextView = itemView.findViewById(R.id.idDepartment)
        val fresher: TextView = itemView.findViewById(R.id.idFresher)
        val remark: TextView = itemView.findViewById(R.id.idRemark)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int = details.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = details[position]
        holder.nameTextview.text = note.name
        holder.addressTextview.text = note.address
        holder.genderView.text = note.gender
        holder.department.text = note.department.toString()
        holder.fresher.text = note.fresher.toString()
        holder.remark.text = note.remarks

    }

}