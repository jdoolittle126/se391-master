package edu.neit.jonathandoolittle.candylab

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MyActivityResultContract: ActivityResultContract<CandyOrder, String>() {

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return "we back!";
    }

    override fun createIntent(context: Context, input: CandyOrder): Intent {
        return Intent(context, OrdersActivity::class.java).apply {
            putExtra("data", input)
        }
    }
}