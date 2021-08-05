package edu.neit.jonathandoolittle.w3_inclass_activity_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val editTextTopText = findViewById<TextView>(R.id.editTextTopText)
        val editTextBottomText = findViewById<TextView>(R.id.editTextBottomText)


        return when(item.itemId) {
            R.id.optionNew -> {
                editTextBottomText.text = ""
                editTextTopText.text = ""
                true
            }
            R.id.optionToast -> {
                Toast.makeText(applicationContext, editTextTopText.text.toString(), Toast.LENGTH_LONG).show()
               true
            }
            R.id.optionLong -> {
                Toast.makeText(applicationContext, editTextBottomText.text.toString(), Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}