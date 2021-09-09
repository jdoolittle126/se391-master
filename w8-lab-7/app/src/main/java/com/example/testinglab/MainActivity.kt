package com.example.testinglab

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.testinglab.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //setContentView(R.layout.activity_main)
    }

    fun CalculateMonthlyPayment(view: View) {

        val MonthlyPayment : Double = Calculations.determineMortagePayment(
            binding.AmountOfLoan.text.toString().toDouble(),
            binding.InterestRate.text.toString().toDouble()/ 12,
            binding.NumberOfYears.text.toString().toDouble() * 12)
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance()
        val formattedMonthlyPayment: String = formatter.format(MonthlyPayment)
        binding.tvMonthlyPayment.text = formattedMonthlyPayment;

    }

    fun CalculateMonthlyPaymentBad(view: View)
    {
        val MonthlyPayment : Double = Calculations.determineMortagePaymentBad(
            binding.AmountOfLoan.text.toString().toDouble(),
            binding.InterestRate.text.toString().toDouble()/ 12,
            binding.NumberOfYears.text.toString().toDouble() * 12)
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance()
        val formattedMonthlyPayment: String = formatter.format(MonthlyPayment)
        binding.tvMonthlyPayment.text = formattedMonthlyPayment;
    }



}