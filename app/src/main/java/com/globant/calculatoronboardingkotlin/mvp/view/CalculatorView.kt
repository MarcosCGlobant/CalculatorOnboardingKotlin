package com.globant.calculatoronboardingkotlin.mvp.view

import android.app.Activity
import kotlinx.android.synthetic.main.activity_main.calculation_label
import kotlinx.android.synthetic.main.activity_main.input_label

class CalculatorView(activity: Activity) : ActivityView(activity) {

    fun showResult(result: String?) {
        activity?.calculation_label?.text = result
    }

    fun showNumberPressed(number: String?) {
        activity?.input_label?.text = number
    }

    fun showOperationPressed(operator: String?) {
        activity?.input_label?.text = operator
    }

}