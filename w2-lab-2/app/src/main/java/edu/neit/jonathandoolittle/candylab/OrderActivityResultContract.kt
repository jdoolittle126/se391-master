package edu.neit.jonathandoolittle.candylab

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

/**
 *
 * Defines a contract between the candy order form ([MainActivity]) and the
 * order display view ([OrdersActivity]). This contract provides a candy order from [MainActivity]
 * to [OrdersActivity], and once the activity is finished, [OrdersActivity] returns the number of
 * orders present back to [MainActivity]
 *
 * @author Jonathan Doolittle
 * @version 0.1 - 7/30/2021
 *
 */

class OrderActivityResultContract: ActivityResultContract<CandyOrder, Int>() {

    // Some constants to make it easy to store/retrieve data
    companion object {
        const val orderKey = "data"
        const val defaultValue = -1
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int {
        return intent?.extras?.getInt(orderKey) ?: defaultValue;
    }

    override fun createIntent(context: Context, input: CandyOrder): Intent {
        return Intent(context, OrdersActivity::class.java).apply {
            putExtra(orderKey, input)
        }
    }
}