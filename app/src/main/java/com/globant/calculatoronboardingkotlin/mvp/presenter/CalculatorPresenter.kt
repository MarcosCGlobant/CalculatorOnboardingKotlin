package com.globant.calculatoronboardingkotlin.mvp.presenter

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDING_BY_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ONE_INT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ZERO_DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ZERO_INT

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
        if (model.firstNumber.isNotEmpty() && model.operator.isEmpty()) {
            model.firstNumber = deleteAndShowValue(model.firstNumber)
        } else if (model.operator.isNotEmpty() && model.secondNumber.isEmpty()) {
            model.operator = deleteAndShowValue(model.operator)
        } else if (model.secondNumber.isNotEmpty()) {
            model.secondNumber = deleteAndShowValue(model.secondNumber)
        }
    }

    override fun onActionButtonPressed(action: String) {
        if (model.firstNumber.isNotEmpty()) {
            if (model.operator.isNotEmpty()) {
                if (model.secondNumber.isNotEmpty()) {
                    model.firstNumber = model.calculateResult()
                    view.showResult(model.firstNumber)
                    model.operator = action
                    view.showInputPressed(model.operator)
                    model.secondNumber = EMPTY_STRING
                } else {
                    view.showTooManyOperatorsError()
                }
            } else {
                model.operator = action
                view.showInputPressed(model.operator)
            }
        } else {
            view.showOperatorWithNoNumberError()
        }
    }

    override fun onEqualsButtonPressed() {
        val result: String
        if ((model.firstNumber.isNotEmpty()) && (model.secondNumber.isNotEmpty()) &&
            (model.firstNumber != ZERO_DOT) && (model.secondNumber != ZERO_DOT)
        ) {
            result = model.calculateResult()
            if (result == DIVIDING_BY_ZERO) {
                view.showDivideByZeroError()
                onClearButtonPressed()
            } else {
                view.showResult(result)
            }
            model.clear()
        } else if (model.operator.isEmpty() && (model.firstNumber != ZERO_DOT)) {
            view.showResult(model.firstNumber)
            model.clear()
        } else {
            view.showInvalidOperationError()
            onClearButtonPressed()
        }
    }

    override fun onNumberButtonPressed(number: String) {
        if (model.operator == EMPTY_STRING) {
            if (model.secondNumber.isEmpty()) {
                model.firstNumber = "${model.firstNumber}$number"
                view.showInputPressed(model.firstNumber)
            }
        } else {
            model.secondNumber = "${model.secondNumber}$number"
            view.showInputPressed(model.secondNumber)
        }
    }

    override fun onDotButtonPressed(dot: String) {
        if ((model.secondNumber.isEmpty()) && (model.operator.isEmpty())) {
            if (model.firstNumber.isEmpty()) {
                model.firstNumber = "${NUMBER_ZERO}$dot"
                view.showInputPressed(model.firstNumber)
            } else if (!model.firstNumber.contains(DOT)) {
                model.firstNumber = "${model.firstNumber}$dot"
                view.showInputPressed(model.firstNumber)
            } else {
                view.showTooManyDotsError()
            }
        } else if (model.operator.isNotEmpty()) {
            if (model.secondNumber.isEmpty()) {
                model.secondNumber = "${NUMBER_ZERO}$dot"
                view.showInputPressed(model.secondNumber)
            } else if (!model.secondNumber.contains(DOT)) {
                model.secondNumber = "${model.secondNumber}$dot"
                view.showInputPressed(model.secondNumber)
            } else {
                view.showTooManyDotsError()
            }
        }
    }


    private fun deleteAndShowValue(value: String): String {
        var toReduce = value
        if (toReduce.isNotEmpty()) {
            toReduce = toReduce.substring(ZERO_INT, value.length - ONE_INT)
            view.showInputPressed(toReduce)
        }
        return toReduce
    }
}