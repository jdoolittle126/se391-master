package edu.neit.jonathandoolittle.w1_group_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     *
     */
    fun saveAccount(view: View) {
        val editTextName = findViewById<EditText>(R.id.txtName)
        val radioGroupAccountType = findViewById<RadioGroup>(R.id.rbSavingsType)
        val radioButtonAccountType = findViewById<RadioButton>(radioGroupAccountType.checkedRadioButtonId)
        val editTextBalance = findViewById<EditText>(R.id.txtAmount)

        val name: String = editTextName.text.toString()

        val accountType: String = when(radioButtonAccountType.text.toString()) {
            "Checking" ->  "Checking Account"
            "Savings" ->  "Savings Account"
            else -> "Other"
        }

        val balance: Float = editTextBalance.text.toString().toFloat()

        val myAccount = BankAccount(name, accountType, balance)

        Toast.makeText(applicationContext, myAccount.toString(),Toast.LENGTH_LONG).show()

    }
}