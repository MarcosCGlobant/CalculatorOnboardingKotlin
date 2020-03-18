package com.globant.calculatoronboardingkotlin.mvp.presenter

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING

class CalculatorPresenter(
    private val model: CalculatorContracts.Model,
    private val view: CalculatorContracts.View
) : CalculatorContracts.Presenter {

    override fun onClearButtonPressed() {
        model.clear()
        view.showInputPressed(EMPTY_STRING)
        view.showResult(EMPTY_STRING)
    }

    override fun onDeleteCurrentNumberButtonPressed() {
        // TODO next task
    }

    override fun onActionButtonPressed(action: String) {
        model.operator = action
        view.showInputPressed(model.operator)
    }

    override fun onEqualsButtonPressed() {
        // TODO next task
    }

    override fun onNumberButtonPressed(number: String) {
        model.firstNumber = number
        view.showInputPressed(model.firstNumber)
    }
}