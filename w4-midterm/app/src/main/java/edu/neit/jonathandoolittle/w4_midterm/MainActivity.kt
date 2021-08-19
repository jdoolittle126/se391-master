package edu.neit.jonathandoolittle.w4_midterm

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.submitBalance -> {
                val i = Intent(this, ResultActivity::class.java)

                val buttonId = findViewById<RadioGroup>(R.id.radioGroupAction).checkedRadioButtonId
                val type = findViewById<RadioButton>(buttonId).text.toString()
                val value = findViewById<EditText>(R.id.editTextDollarAmount).text.toString().toFloat()

                i.putExtra("type", type)
                i.putExtra("value", value)
                startActivityForResult(i,REQUEST_CODE)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            if (data?.hasExtra("balance") != null) {
                val result = data.extras!!.getString("balance")
                findViewById<TextView>(R.id.textViewBalance).text = getString(R.string.current_balance, result!!.toFloat())
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }


}