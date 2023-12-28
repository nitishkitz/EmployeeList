package com.example.employeelist



data class Employee(

    var id: Int,
    val name: String,
    val address: String,
    val gender: String="",
    val department:String?="",
    val fresher: Any?,
    val remarks: String

)

