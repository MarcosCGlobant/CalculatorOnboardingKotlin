package com.globant.calculatoronboardingkotlin.mvp.contracts

import com.globant.calculatoronboardingkotlin.mvp.presenter.ErrorMessages

interface CalculatorContracts {
    interface Model {
        var firstNumber: String
        var secondNumber: String
        var operator: String
        fun clear()
    }

    interface View {
        fun showResult(result: String)
        fun showInputPressed(operator: String)
        fun showError(enum: ErrorMessages)
    }

    interface Presenter {
        fun onClearButtonPressed()
        fun onDeleteCurrentNumberButtonPressed()
        fun onActionButtonPressed(action: String)
        fun onEqualsButtonPressed()
        fun onNumberButtonPressed(number: String)
        fun onDotButtonPressed(dot: String)
    }
}