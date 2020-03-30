package com.globant.calculatoronboardingkotlin.mvp.presenter

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDING_BY_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.INPUTS_ARE_FULL
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.OPERATOR_WITH_NO_NUMBER
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.TOO_MANY_DOTS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.TOO_MANY_OPERATORS

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
        val reducedValue = model.deleteValue()
        view.showInputPressed(reducedValue)
    }

    override fun onActionButtonPressed(action: String) {
        when (val operator = model.addOperator(action)) {
            TOO_MANY_OPERATORS -> {
                view.showTooManyOperatorsError()
            }
            OPERATOR_WITH_NO_NUMBER -> {
                view.showOperatorWithNoNumberError()
            }
            INPUTS_ARE_FULL -> {
                view.showInputsAreFullError()
            }
            else -> {
                view.showInputPressed(operator)
            }
        }
    }

    override fun onEqualsButtonPressed() {
        val result: String
        if (model.isOperationCompatible()) {
            result = model.calculateResult()
            if (result == DIVIDING_BY_ZERO) {
                view.showDivideByZeroError()
                onClearButtonPressed()
            } else {
                view.showResult(result)
            }
        } else {
            view.showInvalidOperationError()
            onClearButtonPressed()
        }
    }

    override fun onNumberButtonPressed(number: String) {
        view.showInputPressed(model.appendNumber(number))
    }

    override fun onDotButtonPressed(dot: String) {
        val addedDot = model.appendDot(dot)
        if (addedDot == TOO_MANY_DOTS) {
            view.showTooManyDotsError()
        } else {
            view.showInputPressed(addedDot)
        }
    }
}