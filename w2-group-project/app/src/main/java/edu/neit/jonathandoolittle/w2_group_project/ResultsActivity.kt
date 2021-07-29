package edu.neit.jonathandoolittle.w2_group_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        var extras = intent.extras
        val inputString = extras?.getString("yourkey")
        val displayIntentText = findViewById<EditText>(R.id.returnValue)
        displayIntentText.setText(inputString)
    }

    override fun finish() {
        val i = Intent()
        val returnValue = findViewById<EditText>(R.id.returnValue)
        val string = returnValue.text.toString()
        i.putExtra("returnkey", string)
        setResult(Activity.RESULT_OK, i)
        super.finish()
    }


}