package com.android.neit.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.neit.myapplication.R

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val extras = intent.extras
        val inputString = extras!!.getString("results")
        val view = findViewById(R.id.tvSentValue) as TextView
        view.text = inputString
    }

    fun returnToMain(v: View?): View? {
        finish()
        return v
    }

    override fun finish() {
        val intent = Intent()
        val txtResult = findViewById(R.id.returnValue) as EditText
        val Resultstring = txtResult.text.toString()
        intent.putExtra("returnkey", Resultstring)
        setResult(RESULT_OK, intent)
        super.finish()
    }
}