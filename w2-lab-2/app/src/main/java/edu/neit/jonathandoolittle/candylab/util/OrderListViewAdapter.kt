package edu.neit.jonathandoolittle.candylab.util

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import edu.neit.jonathandoolittle.candylab.CandyOrder

/**
 *
 * Provides a display adapter for a list of [CandyOrders][CandyOrder]. This allows for orders to be
 * displayed. Currently, this class is confined to the android.R.layout.simple_list_item_2 layout
 *
 * ## Usage
 *
 *      val listViewOrders = findViewById<ListView>(R.id.listViewOrders)
 *      val listViewAdapter = OrderListViewAdapter(this, candyOrdersCompanion)
 *      listViewOrders.adapter = listViewAdapter
 *
 * @author Jonathan Doolittle
 * @version 0.1 - 7/30/2021
 *
 * @property applicationContext The [Activity] that spawns this adapter
 * @property orders An array list of [CandyOrder] objects to be displayed
 *
 */
class OrderListViewAdapter(private val applicationContext: Activity, private val orders: ArrayList<CandyOrder>) :
    ArrayAdapter<CandyOrder>(applicationContext, android.R.layout.simple_list_item_2, orders) {

    // ******************************
    // Public methods
    // ******************************

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listItemView = convertView

        // If the current convertView is null, inflate and define it. Otherwise, recycle
        if(listItemView == null) {
            val layoutInflater = applicationContext.layoutInflater
            listItemView = layoutInflater.inflate(android.R.layout.simple_list_item_2, null, true)
            if(listItemView == null) {
                // TODO Look into better options here
                return super.getView(position, convertView, parent)
            }
        }

        val listItemHeader = listItemView.findViewById<TextView>(android.R.id.text1)
        val listItemDetails = listItemView.findViewById<TextView>(android.R.id.text2)

        // Populate data
        listItemHeader.text = String.format("%s %s", orders[position].customerFirstName, orders[position].customerLastName)
        listItemDetails.text = String.format("%s - %d", orders[position].candyBarType.toString(), orders[position].candyBarQuantity)
                                .plus(if(orders[position].expeditedShipping) {
                                    "\uD83D\uDE9A" // Truck emoji :)
                                } else  {
                                    ""
                                })
        return listItemView

    }

}