package com.globant.calculatoronboardingkotlin.mvp.presenter

import com.globant.calculatoronboardingkotlin.mvp.model.CalculatorModel
import com.globant.calculatoronboardingkotlin.mvp.view.CalculatorView
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING

class CalculatorPresenter(private val model: CalculatorModel, private val view: CalculatorView) {

    fun onClearButtonPressed() {
        model.clear()
        view.showOperationPressed(EMPTY_STRING)
        view.showResult(EMPTY_STRING)
    }

    fun onDeleteCurrentNumberButtonPressed() {
        // TODO next task
    }

    fun onActionButtonPressed(action: String) {
        model.operator = action
        view.showOperationPressed(model.operator)
    }

    fun onEqualsButtonPressed() {
        // TODO next task
    }

    fun onNumberButtonPressed(number: String) {
        model.firstNumber = number
        view.showNumberPressed(model.firstNumber)
    }

}