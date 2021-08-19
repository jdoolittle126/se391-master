package edu.neit.jonathandoolittle.w1_lab_1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import edu.neit.jonathandoolittle.w1_lab_1.MainActivity.OrderList
import edu.neit.jonathandoolittle.w1_lab_1.util.EditTextValidation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateOrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var myView : View
    private var fragmentListener: OrderCreationEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_create_order, container, false)

        return myView
    }

    /**
     * Creates a new [CandyOrder] based on the data entered in the order form. If faced with
     * invalid input, no order will be added to the [OrderList]
     *
     */
    fun createOrder() {
        // Find everything by ID
        val editTextFirstName = myView.findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = myView.findViewById<EditText>(R.id.editTextLastName)
        val spinnerTypeOfChocolate = myView.findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val editTextNumberOfBars = myView.findViewById<EditText>(R.id.editTextNumberOfBars)
        val radioGroupShipmentType = myView.findViewById<RadioGroup>(R.id.radioGroupShipmentType)
        val radioButtonShipmentType = myView.findViewById<RadioButton>(radioGroupShipmentType.checkedRadioButtonId)
        val textViewOrderAdded = myView.findViewById<TextView>(R.id.textViewOrderAdded)

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
        OrderList.candyOrdersCompanion.add(order)

        // ORDER CONFIRM
        textViewOrderAdded.text = resources.getQuantityString(R.plurals.order_added, OrderList.candyOrdersCompanion.size, OrderList.candyOrdersCompanion.size)

        // For demo
        //Toast.makeText(applicationContext, order.toString(), Toast.LENGTH_SHORT).show()

    }

    /**
     * Populates the spinner with all of the values in [CandyBarType]
     */
    private fun populateUi() {
        val spinnerTypeOfChocolate = myView.findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val dataAdapter = ArrayAdapter(myView.context, android.R.layout.simple_spinner_dropdown_item, CandyBarType.values())
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTypeOfChocolate.adapter = dataAdapter
    }

    /**
     * Resets the order form to default values
     */
    private fun clearUi() {
        // Find everything by ID
        val editTextFirstName = myView.findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = myView.findViewById<EditText>(R.id.editTextLastName)
        val spinnerTypeOfChocolate = myView.findViewById<Spinner>(R.id.spinnerTypeOfChocolate)
        val editTextNumberOfBars = myView.findViewById<EditText>(R.id.editTextNumberOfBars)
        val radioButtonShipmentNormal = myView.findViewById<RadioButton>(R.id.radioButtonShipmentNormal)

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

    // --------------------

    fun LoadFragmentData(data: String?) {
        var txtValue = myView.findViewById<TextView>(R.id.txtValue)
        txtValue.text = data
    }

    fun onButtonPressed(valueToPass: String) {
        fragmentListener?.sendBlankFragmentValue(valueToPass)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            fragmentListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    /**
     * TODO
     */
    interface OrderCreationEventListener {
        /**
         * TODO
         */
        fun onOrderCreate(candyOrder: CandyOrder)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}