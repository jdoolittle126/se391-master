package com.example.testinglab

class Calculations {

    companion object {

        fun determineMortagePayment(
            principal: Double,
            interestRate: Double,
            months: Double
        ): Double {
            return principal * interestRate / (1 - Math.pow((1 + interestRate).toDouble(), -months))
        }

        fun determineMortagePaymentBad(
            principal: Double,
            interestRate: Double,
            months: Double
        ): Double {
            return 1.5 * (principal * interestRate / (1 - Math.pow(
                (1 + interestRate).toDouble(),
                -months
            )))
        }
    }
}