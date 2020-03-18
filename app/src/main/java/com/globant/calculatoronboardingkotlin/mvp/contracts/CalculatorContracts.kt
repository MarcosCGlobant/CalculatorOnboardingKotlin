package com.globant.calculatoronboardingkotlin.mvp.contracts

interface CalculatorContracts {
    interface Model {
        var firstNumber: String?
        var secondNumber: String?
        var operator: String?
        fun clear()
    }

    interface View {
        fun showResult(result: String?)
        fun showInputPressed(operator: String?)
    }

    interface Presenter {
        fun onClearButtonPressed()
        fun onDeleteCurrentNumberButtonPressed()
        fun onActionButtonPressed(action: String)
        fun onEqualsButtonPressed()
        fun onNumberButtonPressed(number: String)
    }
}