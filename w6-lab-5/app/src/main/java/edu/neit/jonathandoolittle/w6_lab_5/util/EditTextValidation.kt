package edu.neit.jonathandoolittle.w6_lab_5.util

import android.widget.EditText

/**
 *
 * Provides basic validation methods for [EditText] widgets. Errors are automatically applied to
 * the EditText, and a boolean is returned providing the validation status of the element. This class
 * currently provides the following validation functionality:
 * - Validate that an input is not blank [validateIsFilled]
 * - Validate that a numeric input is larger than x [validateNumericIsGreaterThan]
 *
 * @author Jonathan Doolittle
 * @version 0.1 - 7/27/2021
 *
 */

class EditTextValidation {

    // ******************************
    // Public methods
    // ******************************

    /**
     * Validates if the input of [editText] is larger than [greaterThan]. Does not
     * validate that [editText] is numeric. Any previous error on [editText] will be
     * cleared.
     *
     * ## Usage
     *
     *      val editTextValidation = EditTextValidation()
     *      val isValid: Boolean = editTextValidation.validateNumericIsGreaterThan(myOtherEditText, 50.0f, "Only numbers larger than 50 are allowed!")
     *
     * @param editText The [EditText] to validate
     * @param greaterThan The exclusive lower-bound for [editText]
     * @param errorMessage The message to display on [editText] when the condition is not met
     * @return True if the value of [editText] is larger than [greaterThan]
     */
    fun validateNumericIsGreaterThan(editText: EditText, greaterThan: Float, errorMessage: String): Boolean {
        if(editText.text.toString().toFloat() <= greaterThan) {
            editText.error = errorMessage;
            return false;
        }

        editText.error = null;
        return true;
    }

    /**
     * Validates that the input of [editText] is not an empty string. Any previous error on [editText] will be
     * cleared.
     *
     * ## Usage
     *
     *      val editTextValidation = EditTextValidation()
     *      val isValid: Boolean = editTextValidation.validateIsFilled(myEditText, "This box cannot be left blank!")
     *
     * @param editText The [EditText] to validate
     * @param errorMessage The message to display on [editText] if it is empty
     * @return True if the value of [editText] is not empty
     */
    fun validateIsFilled(editText: EditText, errorMessage: String): Boolean {
        if(editText.text.toString().isEmpty()) {
            editText.error = errorMessage;
            return false;
        }

        editText.error = null;
        return true;
    }

}