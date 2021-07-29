package edu.neit.jonathandoolittle.w2_group_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            if (data?.hasExtra("returnkey") != null) {
                val result = data.extras!!.getString("returnkey")
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    fun onClick(view: View) {
        val i = Intent(this, ResultsActivity::class.java)
        val inputForIntent = findViewById<EditText>(R.id.inputforintent)
        val string = inputForIntent.text.toString()
        i.putExtra("yourkey", string)
        startActivityForResult(i,REQUEST_CODE)
    }

}