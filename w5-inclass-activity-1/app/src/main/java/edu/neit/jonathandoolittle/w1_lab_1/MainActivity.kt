package edu.neit.jonathandoolittle.w1_lab_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.neit.jonathandoolittle.CandyLab
import edu.neit.jonathandoolittle.w1_lab_1.util.EditTextValidation

class MainActivity : AppCompatActivity(), CreateOrderFragment.OrderCreationEventListener, CandyOrderFragment.CandyListInteractionListener {

    /**
     * Provides a static array to hold [CandyOrder]s
     */
    companion object OrderList {
        var candyOrdersCompanion = ArrayList<CandyOrder>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onOrderCreate(candyOrder: CandyOrder) {
        var listFragment : CandyOrderFragment = this.supportFragmentManager.findFragmentById(R.id.fragmentOrdersList) as CandyOrderFragment;
        // Add to static object
        candyOrdersCompanion.add(candyOrder)
        // Notify list of change
        listFragment.onOrderAdded()

        // Toast total list size
        Toast.makeText(
            CandyLab.appContext,
            resources.getQuantityString(R.plurals.order_added, candyOrdersCompanion.size, candyOrdersCompanion.size),
            Toast.LENGTH_LONG).show()
    }

    override fun onListItemClicked(candyOrder: CandyOrder) {
        var createFragment : CreateOrderFragment = this.supportFragmentManager.findFragmentById(R.id.fragmentCreateOrder) as CreateOrderFragment;
        // Load order to UI
        createFragment.onOrderLoad(candyOrder)
    }


}