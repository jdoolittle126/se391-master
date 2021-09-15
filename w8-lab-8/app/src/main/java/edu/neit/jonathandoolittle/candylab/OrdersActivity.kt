package edu.neit.jonathandoolittle.candylab

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.EditText
import edu.neit.jonathandoolittle.candylab.util.OrderListViewAdapter

/**
 *
 * OrdersActivity provides a screen that displays all of the active orders in a list,
 * as well as presenting the user with a button to return to the order form ([MainActivity])
 *
 * @author Jonathan Doolittle
 * @version 0.1 - 7/30/2021
 *
 */

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        // Gather the incoming order object
        val extras = intent.extras
        val order = extras?.get(OrderActivityResultContract.orderKey)

        if(!storeOrder(order)) {
            // Shouldn't happen, but notifies just in case!
            Toast.makeText(applicationContext, getString(R.string.generic_error_occurred), Toast.LENGTH_LONG).show();
        }

        populateList()
    }

    override fun finish() {
        // Return the size of the order list
        val returnIntent = Intent()
        setResult(Activity.RESULT_OK, returnIntent)
        super.finish()
    }


    /**
     * Returns to the [MainActivity]
     *
     * @param view The view that is calling this function
     */
    fun returnToMain(view: View) {
        finish()
    }

    fun searchByPrice(view: View) {
        val editTextSearchPrice = findViewById<EditText>(R.id.editTextSearchByPrice)
        val price = editTextSearchPrice.text.toString().toFloatOrNull()

        if(price != null) {
            populateList(getDatabase()?.getWherePriceGreatThan(price))
        }

    }

    /**
     * Populates the [ListView] with the orders on record. Uses a custom adapter
     * to display each list item.
     *
     */
    private fun populateList(items: List<CandyOrder>? = null) {
        val listViewOrders = findViewById<ListView>(R.id.listViewOrders)

        val adapterValues = items ?: (getDatabase()?.getAll() ?: listOf())

        val listViewAdapter = OrderListViewAdapter(this, adapterValues as ArrayList<CandyOrder>)
        listViewOrders.adapter = listViewAdapter
    }

    private fun getDatabase(): CandyOrderDao? {
        return CandyOrderDatabase.getDatabase(applicationContext)?.candyOrderDao()
    }

    /**
     * Stores the provided order, returning a boolean value based on success
     *
     * @param order The order to add to the records
     * @return True, if the order was parsed correctly
     */
    private fun storeOrder(order: Any?): Boolean {
        if(order !is CandyOrder) {
            return false;
        }
        getDatabase()?.insert(order)
        return true;
    }

}