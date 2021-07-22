package edu.neit.jonathandoolittle.w1_inclass_activity_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * @param inputText A TextView
     * @return The input's value as a Float
     */
    private fun asFloat(inputText: TextView): Float {
        return inputText.text.toString().toFloat()
    }

    /**
     * Adds or subtracts the two values placed in the TextViews
     * @param view The view that represents the clicked item
     */
    fun calculateValue(view: View) {

        // Gather Objects by ID
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val textEditNumberOne = findViewById<EditText>(R.id.editTextNumberOne)
        val textEditNumberTwo = findViewById<EditText>(R.id.editTextNumberTwo)
        val radioGroupOperand = findViewById<RadioGroup>(R.id.radioGroupOperand)
        val radioButtonOperand = findViewById<RadioButton>(radioGroupOperand.checkedRadioButtonId)

        // Watch for invalid input
        try {
            val numberOne: Float = asFloat(textEditNumberOne)
            val numberTwo: Float = asFloat(textEditNumberTwo)

            textViewResult.text = when(radioButtonOperand.text.toString()) {
                getString(R.string.add) -> (numberOne + numberTwo).toString()
                getString(R.string.subtract) -> (numberOne - numberTwo).toString()
                else -> getString(R.string.invalid)
            }

        } catch (e: Exception) {
            textViewResult.text = getString(R.string.invalid)
        }


    }

}