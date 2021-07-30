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

    /**
     * Provides a static array to hold [CandyOrders][CandyOrder]
     */
    companion object OrderList {
        var candyOrdersCompanion = ArrayList<CandyOrder>()
    }

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
        returnIntent.putExtra(OrderActivityResultContract.orderKey, candyOrdersCompanion.size)
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

    /**
     * Populates the [ListView] with the orders on record. Uses a custom adapter
     * to display each list item.
     *
     */
    private fun populateList() {
        val listViewOrders = findViewById<ListView>(R.id.listViewOrders)
        val listViewAdapter = OrderListViewAdapter(this, candyOrdersCompanion)
        listViewOrders.adapter = listViewAdapter
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
        candyOrdersCompanion.add(order)
        return true;
    }

}