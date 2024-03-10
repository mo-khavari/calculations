package com.jetbrains.GPAcalculation
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class PercentageActivity : AppCompatActivity() {

    private lateinit var buttonGradeSolve: Button
    private lateinit var checkBoxes: List<CheckBox>
    private lateinit var editTextsCorrect: List<EditText>
    private lateinit var editTextsWrong: List<EditText>
    private lateinit var editTextsTotal: List<EditText>
    private lateinit var editTextsName: List<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.percentage_of_quiz)
        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener{
            val intetent = Intent(this@PercentageActivity, MainActivity::class.java)
            startActivity(intetent)
        }
        val buttonGradeSolve: Button = findViewById(R.id.buttongardesolve)
        buttonGradeSolve.setOnClickListener {
            calculateAndShowGrades()
        }
    }
    private fun calculateAndShowGrades() {
        val correctEditTexts = arrayOf(
            findViewById<EditText>(R.id.correct1),
            findViewById<EditText>(R.id.correct2),
            findViewById<EditText>(R.id.correct3),
            findViewById<EditText>(R.id.correct4),
            findViewById<EditText>(R.id.correct5),
            findViewById<EditText>(R.id.correct6),
            findViewById<EditText>(R.id.correct7),
            findViewById<EditText>(R.id.correct8),
            findViewById<EditText>(R.id.correct9),
            findViewById<EditText>(R.id.correct10)
        )

        val wrongEditTexts = arrayOf(
            findViewById<EditText>(R.id.wrong1),
            findViewById<EditText>(R.id.wrong2),
            findViewById<EditText>(R.id.wrong3),
            findViewById<EditText>(R.id.wrong4),
            findViewById<EditText>(R.id.wrong5),
            findViewById<EditText>(R.id.wrong6),
            findViewById<EditText>(R.id.wrong7),
            findViewById<EditText>(R.id.wrong8),
            findViewById<EditText>(R.id.wrong9),
            findViewById<EditText>(R.id.wrong10)
        )

        val totalEditTexts = arrayOf(
            findViewById<EditText>(R.id.total1),
            findViewById<EditText>(R.id.total2),
            findViewById<EditText>(R.id.total3),
            findViewById<EditText>(R.id.total4),
            findViewById<EditText>(R.id.total5),
            findViewById<EditText>(R.id.total6),
            findViewById<EditText>(R.id.total7),
            findViewById<EditText>(R.id.total8),
            findViewById<EditText>(R.id.total9),
            findViewById<EditText>(R.id.total10)
        )

        val nameEditTexts = arrayOf(
            findViewById<EditText>(R.id.name1),
            findViewById<EditText>(R.id.name2),
            findViewById<EditText>(R.id.name3),
            findViewById<EditText>(R.id.name4),
            findViewById<EditText>(R.id.name5),
            findViewById<EditText>(R.id.name6),
            findViewById<EditText>(R.id.name7),
            findViewById<EditText>(R.id.name8),
            findViewById<EditText>(R.id.name9),
            findViewById<EditText>(R.id.name10)
        )

        val checkBoxes = arrayOf(
            findViewById<CheckBox>(R.id.checkBox1),
            findViewById<CheckBox>(R.id.checkBox2),
            findViewById<CheckBox>(R.id.checkBox3),
            findViewById<CheckBox>(R.id.checkBox4),
            findViewById<CheckBox>(R.id.checkBox5),
            findViewById<CheckBox>(R.id.checkBox6),
            findViewById<CheckBox>(R.id.checkBox7),
            findViewById<CheckBox>(R.id.checkBox8),
            findViewById<CheckBox>(R.id.checkBox9),
            findViewById<CheckBox>(R.id.checkBox10)
        )

        val gradePercentageList = ArrayList<Double>()
        for (i in correctEditTexts.indices) {
            if (checkBoxes[i].isChecked) {
                val correct = correctEditTexts[i].text.toString().toIntOrNull() ?: 0
                val wrong = wrongEditTexts[i].text.toString().toIntOrNull() ?: 0
                val total = totalEditTexts[i].text.toString().toIntOrNull() ?: 0

                val gradePercentage = calculateGradePercentage(correct, wrong, total)
                gradePercentageList.add(gradePercentage)
            }
        }

        showGradePercentageDialog(gradePercentageList, nameEditTexts)
    }

    private fun calculateGradePercentage(correct: Int, wrong: Int, total: Int): Double {
        val percentage = ((correct * 3) - wrong) / (total * 3.0) * 100
        return String.format("%.2f", percentage).toDouble()
    }


    private fun showGradePercentageDialog(
        percentages: ArrayList<Double>,
        names: Array<EditText>
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Test result")

        val stringBuilder = StringBuilder()
        for (i in percentages.indices) {
            stringBuilder.append(names[i].text.toString())
                .append(": ")
                .append(percentages[i])
                .append("%")
                .append("\n")
        }
        val message = stringBuilder.toString()

        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}