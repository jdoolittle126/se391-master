package edu.neit.jonathandoolittle.candylab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class OrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        val extras = intent.extras
        val order = extras?.get("data")
        Toast.makeText(applicationContext, order.toString(), Toast.LENGTH_SHORT).show()

    }
}