package com.globant.calculatoronboardingkotlin.mvp.contracts

interface CalculatorContracts {
    interface Model {
        var firstNumber: String
        var secondNumber: String
        var operator: String
        fun clear()
        fun calculateResult(): String
        fun appendNumber(number: String): String
        fun appendDot(dot: String): String
        fun addOperator(operation: String): String
        fun deleteValue () : String
        fun isOperationCompatible(): Boolean
    }

    interface View {
        fun showResult(result: String)
        fun showInputPressed(operator: String)
        fun showDivideByZeroError()
        fun showTooManyDotsError()
        fun showTooManyOperatorsError()
        fun showOperatorWithNoNumberError()
        fun showInvalidOperationError()
        fun showInputsAreFullError()
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