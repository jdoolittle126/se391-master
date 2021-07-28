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
        val textViewOrderAdded = findViewById<TextView>(R.id.textViewOrderAdded)

        // RESET NOTIFICATION
        textViewOrderAdded.text = "";

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

        // ORDER SUBMISSION
        candyOrdersCompanion.add(order)

        // ORDER CONFIRM
        textViewOrderAdded.text = resources.getQuantityString(R.plurals.order_added, candyOrdersCompanion.size, candyOrdersCompanion.size)

        // For demo
        //Toast.makeText(applicationContext, order.toString(), Toast.LENGTH_SHORT).show()

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