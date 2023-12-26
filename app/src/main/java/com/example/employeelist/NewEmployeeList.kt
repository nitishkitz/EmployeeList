package com.example.employeelist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewEmployeeList : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var db: DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_employee_list)
        val switch: Switch = findViewById(R.id.Switch)
        val saveBtn: Button = findViewById(R.id.saveButton)
        val discard: Button = findViewById(R.id.discardButton)
        db = DataBaseHelper(this)
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        val spinnerResult = null
        spinnerResult.toString()
        val name: EditText = findViewById(R.id.et_name)
        val address: EditText = findViewById(R.id.et_address)
        saveBtn.setOnClickListener() {
            validateFields()

            saveSelectedOptionToDatabase().toString()

            var maleBtn: RadioButton = findViewById(R.id.radioButton1)
            var femaleBtn: RadioButton = findViewById(R.id.radioButton2)
            if (!switch.isChecked) {
                Toast.makeText(this, "Please enable the switch", Toast.LENGTH_SHORT).show()
                switch.text = "Im Not a Fresher"
            } else {
                switch.text = "Im a fresher"
            }
            if (!maleBtn.isChecked && !femaleBtn.isChecked) {
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()


            } else {
                val male: RadioButton = findViewById(R.id.radioButton1)
                val gender = if (male.isChecked) "Male" else "Female"
                val department = spinner.selectedItem.toString()


                val details = Employee(
                    0,
                    name.text.toString(),
                    address.text.toString(),
                    gender,
                    department,
                    switch.text
                )
                db.insertDetails(details)

                Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }
        }
        discard.setOnClickListener() {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun saveSelectedOptionToDatabase() {


        val radioGroup: RadioGroup = findViewById(R.id.radioGroupgender)

        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        if (selectedRadioButtonId != -1) {
            val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
            val selectedOption = selectedRadioButton.text.toString()

            Toast.makeText(this, "Selected option saved: $selectedOption", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val spinnerItem: String = parent?.getItemAtPosition(position).toString()
        val toast = Toast.makeText(applicationContext, "${spinnerItem}", Toast.LENGTH_SHORT)

        toast.show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        val toast = Toast.makeText(applicationContext, "Select one", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun validateFields() {
        val name: EditText = findViewById(R.id.et_name)
        val address: EditText = findViewById(R.id.et_address)
        val etname = name.text.toString().trim()
        val etaddress = address.toString().trim()
        if (etname.isEmpty() || etaddress.isEmpty()) {
            Toast.makeText(this, "Fill up All fields", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, "Validation Success", Toast.LENGTH_SHORT).show()
    }
}

