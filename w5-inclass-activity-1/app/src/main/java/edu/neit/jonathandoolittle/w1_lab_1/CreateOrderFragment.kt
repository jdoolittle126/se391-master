package edu.neit.jonathandoolittle.w1_lab_1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import edu.neit.jonathandoolittle.CandyLab
import edu.neit.jonathandoolittle.w1_lab_1.MainActivity.OrderList
import edu.neit.jonathandoolittle.w1_lab_1.util.EditTextValidation

/**
 * A [Fragment] that holds an order form for creating [CandyOrder]s
 * Use the [CreateOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateOrderFragment : Fragment() {
    private lateinit var fragmentView : View
    private var fragmentListener: OrderCreationEventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_create_order, container, false)
        populateUi()

        // Set the save button's functionality
        fragmentView.findViewById<Button>(R.id.buttonSaveOrder).setOnClickListener {
            onOrderSave()
        }

        return fragmentView
    }

    /**
     * Checks if the order is null, then loads it to
     * the order form
     *
     * @param order The order to add
     */
    fun onOrderLoad(order: CandyOrder?) {
        if(order != null) {
            loadOrderToForm(order)
        }
    }

    /**
     * Attempts to create an order, and if it is valid,
     * clears the UI and triggers the listener function
     */
    private fun onOrderSave() {
        // If the input is invalid, do nothing
        val order = createOrder() ?: return

        // Clear UI
        clearUi()

        // Trigger listener
        fragmentListener?.onOrderCreate(order)
    }

    /**
     * Creates a new [CandyOrder] based on the data entered in the order form. If faced with
     * invalid input, no order will be returned
     *
     * @return The [CandyOrder] from the inputs, or null if invalid
     */
    private fun createOrder(): CandyOrder? {
        // Find everything by ID
        val editTextFirstName = fragmentView.findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = fragmentView.findViewById<EditText>(R.id.editTextLastName)
        val spinnerTypeOfChocolate = fragmentView.findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val editTextNumberOfBars = fragmentView.findViewById<EditText>(R.id.editTextNumberOfBars)
        val radioGroupShipmentType = fragmentView.findViewById<RadioGroup>(R.id.radioGroupShipmentType)
        val radioButtonShipmentType = fragmentView.findViewById<RadioButton>(radioGroupShipmentType.checkedRadioButtonId)
        val textViewOrderAdded = fragmentView.findViewById<TextView>(R.id.textViewOrderAdded)

        // Reset Notification
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
            false
        }

        // Exit if data is invalid
        if(!isValid) {
            return null
        }

        // Grab all of the data
        val orderFirstName = editTextFirstName.text.toString()
        val orderLastName = editTextLastName.text.toString()
        val orderTypeOfChocolate = spinnerTypeOfChocolate.selectedItem as CandyBarType
        val orderNumberOfBars = editTextNumberOfBars.text.toString().toInt()
        val orderIsExpeditedShipment = radioButtonShipmentType.text.equals(getString(R.string.expedited_shipment))

        // Creates and returns the new order
        return CandyOrder(orderFirstName, orderLastName, orderTypeOfChocolate, orderNumberOfBars, orderIsExpeditedShipment)
    }

    /**
     * Populates the spinner with all of the values in [CandyBarType]
     */
    private fun populateUi() {
        val spinnerTypeOfChocolate = fragmentView.findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val dataAdapter = ArrayAdapter(fragmentView.context, android.R.layout.simple_spinner_dropdown_item, CandyBarType.values())
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTypeOfChocolate.adapter = dataAdapter
    }

    /**
     * Resets the order form to default values
     */
    private fun clearUi() {
        // Find everything by ID
        val editTextFirstName = fragmentView.findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = fragmentView.findViewById<EditText>(R.id.editTextLastName)
        val spinnerTypeOfChocolate = fragmentView.findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val editTextNumberOfBars = fragmentView.findViewById<EditText>(R.id.editTextNumberOfBars)
        val radioButtonShipmentNormal = fragmentView.findViewById<RadioButton>(R.id.radioButtonShipmentNormal)

        // Reset
        editTextFirstName.setText("")
        editTextLastName.setText("")
        spinnerTypeOfChocolate.setSelection(0)
        radioButtonShipmentNormal.isChecked = true
        editTextNumberOfBars.setText("")
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

    /**
     * Loads a given order to the order form
     * @param order The [CandyOrder] to load
     */
    private fun loadOrderToForm(order: CandyOrder) {
        val editTextFirstName = fragmentView.findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = fragmentView.findViewById<EditText>(R.id.editTextLastName)
        val spinnerTypeOfChocolate = fragmentView.findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val editTextNumberOfBars = fragmentView.findViewById<EditText>(R.id.editTextNumberOfBars)
        val radioGroupShipping = fragmentView.findViewById<RadioGroup>(R.id.radioGroupShipmentType)

        editTextFirstName.setText(order.customerFirstName)
        editTextLastName.setText(order.customerLastName)

        spinnerTypeOfChocolate.setSelection(CandyBarType.values().indexOf(order.candyBarType), true)
        editTextNumberOfBars.setText(order.candyBarQuantity.toString())

        if(order.expeditedShipping) {
            radioGroupShipping.check(R.id.radioButtonShipmentExpedited)
        } else {
            radioGroupShipping.check(R.id.radioButtonShipmentNormal)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OrderCreationEventListener) {
            fragmentListener = context
        } else {
            throw RuntimeException("$context must implement OrderCreationEventListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    /**
     * Provides a listener for when [CandyOrder]s are created in this fragment
     */
    interface OrderCreationEventListener {
        /**
         * Triggered when a [CandyOrder] is created.
         *
         * @param candyOrder The newly created order
         */
        fun onOrderCreate(candyOrder: CandyOrder)
    }

    companion object {
        /**
         * Factory create CreateOrderFragment
         *
         * @return A new instance of fragment CreateOrderFragment.
         */
        @JvmStatic
        fun newInstance() = CreateOrderFragment().apply {}
    }
}