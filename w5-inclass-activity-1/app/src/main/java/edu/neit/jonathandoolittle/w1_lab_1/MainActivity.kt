package edu.neit.jonathandoolittle.w1_lab_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.neit.jonathandoolittle.w1_lab_1.util.EditTextValidation


class MainActivity : AppCompatActivity() {

    /**
     * Provides a static array to hold [CandyOrders][CandyOrder]
     */
    companion object OrderList {
        var candyOrdersCompanion = ArrayList<CandyOrder>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}