package com.globant.calculatoronboardingkotlin.mvp.view

import android.app.Activity
import android.widget.TextView
import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import kotlinx.android.synthetic.main.activity_main.calculation_label
import kotlinx.android.synthetic.main.activity_main.input_label

class CalculatorView(activity: Activity) : ActivityView(activity), CalculatorContracts.View {

    private val calculationLabel: TextView = activity.calculation_label
    private val inputLabel: TextView = activity.input_label

    override fun showResult(result: String?) {
        calculationLabel.text = result
    }

    override fun showInputPressed(operator: String?) {
        inputLabel.text = operator
    }
}