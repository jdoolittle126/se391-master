package edu.neit.jonathandoolittle.candylab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.neit.jonathandoolittle.candylab.util.EditTextValidation


class MainActivity : AppCompatActivity() {

    /**
     * Launches the [OrdersActivity], passing in a [CandyOrder] object. Toasts a response on return.
     */
    private val orderResultsActivityLauncher = registerForActivityResult(OrderActivityResultContract()) {
            result ->
        run { updateOrderCount(result) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateUi()
    }

    /**
     * Creates a new [CandyOrder] based on the data entered in the order form. If faced with
     * invalid input, no order will be added to the [OrderList]
     *
     * @param view The [View] calling this method
     */
    fun createOrder(view: View) {
        // Find everything by ID
        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        val spinnerTypeOfChocolate = findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val editTextNumberOfBars = findViewById<EditText>(R.id.editTextNumberOfBars)
        val radioGroupShipmentType = findViewById<RadioGroup>(R.id.radioGroupShipmentType)
        val radioButtonShipmentType = findViewById<RadioButton>(radioGroupShipmentType.checkedRadioButtonId)


        // VALIDATION
        // This validation is a bit crude, but works. This will evolve depending on the direction this
        // project goes

        val editTextValidation = EditTextValidation();
        var isValid = true;

        // First Name Validation
        isValid = isValid.and(editTextValidation.validateIsFilled(editTextFirstName, format(R.string.item_must_be_filled, R.string.first_name)))

        // Last Name Validation
        isValid = isValid.and(editTextValidation.validateIsFilled(editTextLastName, format(R.string.item_must_be_filled, R.string.last_name)))

        // Number of Bars Validation
        isValid = if(editTextValidation.validateIsFilled(editTextNumberOfBars, format(R.string.item_must_be_filled, R.string.number_of_bars))) {
            isValid.and(editTextValidation.validateNumericIsGreaterThan(editTextNumberOfBars, 0.0f, format(R.string.item_must_be_positive_nonzero, R.string.number_of_bars)))
        } else {
            false;
        }

        // Exit if data is invalid
        if(!isValid) {
            return
        }

        // DATA EXTRACTION
        val orderFirstName = editTextFirstName.text.toString()
        val orderLastName = editTextLastName.text.toString()
        val orderTypeOfChocolate = spinnerTypeOfChocolate.selectedItem as CandyBarType
        val orderNumberOfBars = editTextNumberOfBars.text.toString().toInt()
        val orderIsExpeditedShipment = radioButtonShipmentType.text.equals(getString(R.string.expedited_shipment))

        // ORDER CREATION
        val order = CandyOrder(orderFirstName, orderLastName, orderTypeOfChocolate, orderNumberOfBars, orderIsExpeditedShipment)

        // OPEN ORDER SCREEN
        orderResultsActivityLauncher.launch(order)

    }

    /**
     * Populates the spinner with all of the values in [CandyBarType]
     */
    private fun populateUi() {

        val spinnerTypeOfChocolate = findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, CandyBarType.values())
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTypeOfChocolate.adapter = dataAdapter
    }

    /**
     * Updates the label to display the correct order count
     *
     * @param count The new order count
     */
    private fun updateOrderCount(count: Int) {

        // GET VIEW
        val textViewOrderAdded = findViewById<TextView>(R.id.textViewOrderAdded)

        // RESET NOTIFICATION
        textViewOrderAdded.text = if(count == OrderActivityResultContract.defaultValue) {
            ""
        } else {
            resources.getQuantityString(R.plurals.order_added, count, count)
        }
    }

    /**
     * Formats the strings used to create dynamic error messages,
     * cleaning up the validation code.
     * ## Usage
     *
     *      format(R.string.item_must_be_filled, R.string.number_of_bars)
     *
     * @param baseResource The ID of the string that contains the message, typically an error message
     * @param nameResource The ID of the string that identifies the component uniquely
     * @return The formatted string
     */
    private fun format(baseResource: Int, nameResource: Int): String {
        return String.format(getString(baseResource), getString(nameResource))
    }


}