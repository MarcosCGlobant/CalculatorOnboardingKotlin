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
        showErrorMessage(R.string.divide_by_zero_message)
    }

    override fun showTooManyDotsError() {
        showErrorMessage(R.string.too_many_dots)
    }

    override fun showTooManyOperatorsError() {
        showErrorMessage(R.string.too_many_operators)
    }

    override fun showOperatorWithNoNumberError() {
        showErrorMessage(R.string.operator_with_no_number)
    }

    override fun showInvalidOperationError() {
        showErrorMessage(R.string.invalid_operation_message)
    }

    override fun showInputsAreFullError() {
        showErrorMessage(R.string.inputs_are_full)
    }

    private fun showErrorMessage(message: Int) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}