package com.jetbrains.GPAcalculation

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener{
            val intetent2 = Intent(this@MainActivity, PercentageActivity::class.java)
            startActivity(intetent2)
        }
        val btnButton = findViewById<Button>(R.id.buttongardesolve)
        val grade1 = findViewById<EditText>(R.id.grade1)
        val grade2 = findViewById<EditText>(R.id.grade2)
        val grade3 = findViewById<EditText>(R.id.grade3)
        val grade4 = findViewById<EditText>(R.id.grade4)
        val grade5 = findViewById<EditText>(R.id.grade5)
        val grade6 = findViewById<EditText>(R.id.grade6)
        val grade7 = findViewById<EditText>(R.id.grade7)
        val unit1 = findViewById<EditText>(R.id.unit1)
        val unit2 = findViewById<EditText>(R.id.unit2)
        val unit3 = findViewById<EditText>(R.id.unit3)
        val unit4 = findViewById<EditText>(R.id.unit4)
        val unit5 = findViewById<EditText>(R.id.unit5)
        val unit6 = findViewById<EditText>(R.id.unit6)
        val unit7 = findViewById<EditText>(R.id.unit7)
        val check1 = findViewById<CheckBox>(R.id.checkBox1)
        val check2 = findViewById<CheckBox>(R.id.checkBox2)
        val check3 = findViewById<CheckBox>(R.id.checkBox3)
        val check4 = findViewById<CheckBox>(R.id.checkBox4)
        val check5 = findViewById<CheckBox>(R.id.checkBox5)
        val check6 = findViewById<CheckBox>(R.id.checkBox6)
        val check7 = findViewById<CheckBox>(R.id.checkBox7)
        val graderesult = findViewById<TextView>(R.id.result)
        fun calculateGrade() {
            val grades = listOf(grade1, grade2, grade3, grade4, grade5, grade6, grade7)
            val units = listOf(unit1, unit2, unit3, unit4, unit5, unit6, unit7)
            val checkboxes = listOf(check1, check2, check3, check4, check5, check6, check7)
            var totalGrade = 0.0
            var totalUnits = 0

            for (i in grades.indices) {
                if (checkboxes[i].isChecked) {
                    val gradeValue = grades[i].text.toString().toDoubleOrNull()
                    val unitValue = units[i].text.toString().toIntOrNull()

                    if (gradeValue != null && unitValue != null) {
                        totalGrade += gradeValue * unitValue
                        totalUnits += unitValue
                    }
                }
        }

            if (totalUnits > 0) {
                val weightedGrade = totalGrade / totalUnits
                val formattedGrade = String.format("%.2f", weightedGrade)
                // نمایش پنجره با معدل و تعداد واحد و دکمه اوکی
                val builder = AlertDialog.Builder(this)
                builder.setTitle("GPA result")
                builder.setMessage("معدل: $formattedGrade \n تعداد واحد: $totalUnits")
                builder.setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show()
            } else {
                graderesult.text = "لطفاً حداقل یک نمره را انتخاب کنید"
            }
            // بستن کیبورد پس از محاسبه
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
        btnButton.setOnClickListener {
            calculateGrade()
        }
    }
}