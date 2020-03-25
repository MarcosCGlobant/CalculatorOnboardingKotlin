package com.globant.calculatoronboardingkotlin.mvp.view

import android.app.Activity
import android.widget.TextView
import android.widget.Toast
import com.globant.calculatoronboardingkotlin.R
import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import kotlinx.android.synthetic.main.activity_main.calculation_label
import kotlinx.android.synthetic.main.activity_main.input_label

class CalculatorView(activity: Activity) : ActivityView(activity), CalculatorContracts.View {

    private val calculationLabel: TextView = activity.calculation_label
    private val inputLabel: TextView = activity.input_label

    override fun showResult(result: String) {
        calculationLabel.text = result
    }

    override fun showInputPressed(operator: String) {
        inputLabel.text = operator
    }

    override fun showDivideByZeroError() {
        Toast.makeText(activity, R.string.divide_by_zero_message, Toast.LENGTH_SHORT).show()
    }

    override fun showTooManyDotsError() {
        Toast.makeText(activity, R.string.too_many_dots, Toast.LENGTH_SHORT).show()
    }

    override fun showTooManyOperatorsError() {
        Toast.makeText(activity, R.string.too_many_operators, Toast.LENGTH_SHORT).show()
    }

    override fun showOperatorWithNoNumberError() {
        Toast.makeText(activity, R.string.operator_with_no_number, Toast.LENGTH_SHORT).show()
    }

    override fun showInvalidOperationError() {
        Toast.makeText(activity, R.string.invalid_operation_message, Toast.LENGTH_SHORT).show()
    }
}