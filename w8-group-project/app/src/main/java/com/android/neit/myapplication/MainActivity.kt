package com.android.neit.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Calculate(v: View?): View? {
        val number1: EditText = findViewById(R.id.txtNumber1)
        val number2: EditText = findViewById(R.id.txtNumber2)
        val results: TextView = findViewById(R.id.tvSentResult)
        val answer =
            CalculateOperations.CalculateAnswer(number1.text.toString().toFloat(), number2.text.toString().toFloat())
        results.text = java.lang.Float.toString(answer)
        return v
    }




    fun CalcWithOperations(v: View?): View? {
        val number1: EditText = findViewById(R.id.txtNumber1)
        val number2: EditText = findViewById(R.id.txtNumber2)
        val results: TextView = findViewById(R.id.tvSentResult)
        val operation: Spinner = findViewById(R.id.spOperations)
        val sOperation = operation.selectedItem.toString()
        val answer = CalculateOperations.CalculateAnswerWithOperation(
            number1.text.toString().toFloat(), number2.text.toString().toFloat(),
            sOperation
        )
        results.text = java.lang.Float.toString(answer)
        return v
    }

    fun ToNextScreen(v: View?): View? {
        val results = findViewById(R.id.tvSentResult) as TextView
        val string = results.text.toString()
        val i = Intent(this, ResultsActivity::class.java)
        i.putExtra("results", string)
        startActivityForResult(i,REQUEST_CODE)
        return v
    }


    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data?.hasExtra("returnkey") == true) {
                val result = data?.extras!!.getString("returnkey")
                if (result != null && result.length > 0) {
                    val results: TextView = findViewById(R.id.tvSentResult)
                    results.text = result
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}