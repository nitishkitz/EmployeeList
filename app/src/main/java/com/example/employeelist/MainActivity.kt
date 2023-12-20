package com.example.employeelist

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var db: DataBaseHelper
    private lateinit var EmployeeAdapter: EmployeeDetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton:ImageView=findViewById(R.id.addBtn)
        db=DataBaseHelper(this)
        EmployeeAdapter= EmployeeDetailAdapter(db.getAllNotes(),this)

        var recyclerView:RecyclerView=findViewById(R.id.recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=EmployeeAdapter


        addButton.setOnClickListener(){
            val i = Intent(
                applicationContext,
                NewEmployeeList::class.java
            )
            startActivity(i)


        }







    }


}