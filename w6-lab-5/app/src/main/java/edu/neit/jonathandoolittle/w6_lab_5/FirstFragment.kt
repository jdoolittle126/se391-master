package edu.neit.jonathandoolittle.w6_lab_5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import edu.neit.jonathandoolittle.CandyLab
import edu.neit.jonathandoolittle.w6_lab_5.databinding.FragmentFirstBinding
import edu.neit.jonathandoolittle.w6_lab_5.util.EditTextValidation

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        populateUi()

        // Take our arguments, if any, and toast the first name
        arguments?.let {
            val args = FirstFragmentArgs.fromBundle(it)
            if(args.firstName != null) {
                Toast.makeText(activity?.applicationContext, args.firstName, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root

    }

    /**
     * Creates a new [CandyOrder] based on the data entered in the order form. If faced with
     * invalid input, null will be returned
     *
     * @param view The [View] calling this method
     */
    private fun createOrder(): CandyOrder? {

        // VALIDATION
        // This validation is a bit crude, but works. This will evolve depending on the direction this
        // project goes

        val editTextValidation = EditTextValidation();
        var isValid = true;

        // First Name Validation
        isValid = isValid.and(editTextValidation.validateIsFilled(binding.editTextFirstName, format(R.string.item_must_be_filled, R.string.first_name)))

        // Last Name Validation
        isValid = isValid.and(editTextValidation.validateIsFilled(binding.editTextLastName, format(R.string.item_must_be_filled, R.string.last_name)))

        // Number of Bars Validation
        isValid = if(editTextValidation.validateIsFilled(binding.editTextNumberOfBars, format(R.string.item_must_be_filled, R.string.number_of_bars))) {
            isValid.and(editTextValidation.validateNumericIsGreaterThan(binding.editTextNumberOfBars, 0.0f, format(R.string.item_must_be_positive_nonzero, R.string.number_of_bars)))
        } else {
            false;
        }

        // Exit if data is invalid
        if(!isValid) {
            return null
        }

        // DATA EXTRACTION
        val orderFirstName = binding.editTextFirstName.text.toString()
        val orderLastName = binding.editTextLastName.text.toString()
        val orderTypeOfChocolate = binding.spinnerTypeOfChocolate.selectedItem as CandyBarType
        val orderNumberOfBars = binding.editTextNumberOfBars.text.toString().toInt()
        val orderIsExpeditedShipment = binding.radioGroupShipmentType.checkedRadioButtonId == binding.radioButtonShipmentExpedited.id

        // ORDER CREATION
        return CandyOrder(orderFirstName, orderLastName, orderTypeOfChocolate, orderNumberOfBars, orderIsExpeditedShipment)

    }

    /**
     * Populates the spinner with all of the values in [CandyBarType]
     */
    private fun populateUi() {
        activity?.
        baseContext?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                CandyBarType.values()
            )
        }.apply {
            this?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerTypeOfChocolate.adapter = this
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pass first & last name to the second fragment
        binding.buttonSaveOrder.setOnClickListener {
            createOrder()?.let {
                val action = FirstFragmentDirections
                    .actionFirstFragmentToSecondFragment(it.customerFirstName,it.customerLastName)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}