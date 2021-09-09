package com.android.neit.myapplication

class CalculateOperations {

    companion object {
        fun CalculateAnswer(number1: Float, number2: Float): Float {
            return number1 * number2
        }

        fun CalculateAnswerWithOperation(number1: Float, number2: Float, Operation: String?): Float {
            when (Operation) {
                "+" -> return number1 + number2
                "-" -> return number1 - number2
            }
            return number1 * number2
        }
    }
}