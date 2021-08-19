package edu.neit.jonathandoolittle.w4_midterm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    companion object BankAccount {
        var balance = 0.0f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val extras = intent.extras
        val type = extras?.get("type")
        var value = extras?.getFloat("value")

        if(type != null && value != null) {
            when(type) {
                getString(R.string.withdrawal) -> BankAccount.balance -= value
                getString(R.string.deposit) -> BankAccount.balance += value
                getString(R.string.inquiry) -> value = 0.0f;
            }

            findViewById<TextView>(R.id.textViewActionAmount).text = getString(R.string.action, type.toString(), value)
            findViewById<TextView>(R.id.textViewNewBalance).text = getString(R.string.current_balance, BankAccount.balance)
        } else {
            finish()
        }

    }

    fun onCloseClick(view: View) {
        finish()
    }

    override fun finish() {
        val returnIntent = Intent()
        returnIntent.putExtra("balance", BankAccount.balance.toString())
        setResult(Activity.RESULT_OK, returnIntent)
        super.finish()
    }


}