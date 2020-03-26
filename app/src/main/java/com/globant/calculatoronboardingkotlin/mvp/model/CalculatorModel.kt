package com.globant.calculatoronboardingkotlin.mvp.model

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.utils.Constants
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDING_BY_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.INPUTS_ARE_FULL
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MINUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MULTIPLY
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ONE_INT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.OPERATION_WITH_NO_NUMBER
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.PLUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.TOO_MANY_DOTS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.TOO_MANY_OPERATORS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ZERO_DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ZERO_DOUBLE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ZERO_INT

class CalculatorModel : CalculatorContracts.Model {

    override var firstNumber: String = EMPTY_STRING
    override var secondNumber: String = EMPTY_STRING
    override var operator: String = EMPTY_STRING
    var result: String = EMPTY_STRING

    override fun clear() {
        firstNumber = EMPTY_STRING
        secondNumber = EMPTY_STRING
        operator = EMPTY_STRING
        result = EMPTY_STRING
    }

    override fun calculateResult(): String {
        result = when (operator) {
            PLUS -> firstNumber.toDouble() + secondNumber.toDouble()
            MINUS -> firstNumber.toDouble() - secondNumber.toDouble()
            MULTIPLY -> firstNumber.toDouble() * secondNumber.toDouble()
            DIVIDE -> if (secondNumber.toDouble() == ZERO_DOUBLE) {
                return DIVIDING_BY_ZERO
            } else {
                firstNumber.toDouble() / secondNumber.toDouble()
            }
            else -> EMPTY_STRING
        }.toString()
        firstNumber = result
        operator = EMPTY_STRING
        secondNumber = EMPTY_STRING
        return result
    }

    override fun appendNumber(number: String): String {
        var value: String = EMPTY_STRING
        if ((result == firstNumber) && (operator.isEmpty())) {
            clear()
        }

        if (operator.isEmpty()) {
            if (secondNumber.isEmpty()) {
                firstNumber = "${firstNumber}$number"
                value = firstNumber
            }
        } else {
            secondNumber = "${secondNumber}$number"
            value = secondNumber
        }
        return value
    }

    override fun appendDot(dot: String): String {
        var result: String = EMPTY_STRING
        if ((secondNumber.isEmpty()) && (operator.isEmpty())) {
            if (firstNumber.isEmpty()) {
                firstNumber = "${NUMBER_ZERO}$dot"
                result = firstNumber
            } else if (!firstNumber.contains(DOT)) {
                firstNumber = "${firstNumber}$dot"
                result = firstNumber
            } else {
                result = TOO_MANY_DOTS
            }
        } else if (operator.isNotEmpty()) {
            if (secondNumber.isEmpty()) {
                secondNumber = "${NUMBER_ZERO}$dot"
                result = secondNumber
            } else if (!secondNumber.contains(DOT)) {
                secondNumber = "${secondNumber}$dot"
                result = secondNumber
            } else {
                result = TOO_MANY_DOTS
            }
        }
        return result
    }

    override fun addOperator(operation: String): String {
        var addedOperator: String = EMPTY_STRING
        if (firstNumber.isNotEmpty()) {
            if (operator.isNotEmpty() && secondNumber.isEmpty()) {
                addedOperator = TOO_MANY_OPERATORS
            } else if (operator.isEmpty() && secondNumber.isEmpty()) {
                operator = operation
                addedOperator = operator
            } else if (operator.isNotEmpty() && secondNumber.isNotEmpty()) {
                addedOperator = INPUTS_ARE_FULL
            }
        } else {
            addedOperator = OPERATION_WITH_NO_NUMBER
        }
        return addedOperator
    }

    override fun deleteValue(): String {
        var deletedValue: String = EMPTY_STRING

        if (firstNumber.isNotEmpty() && operator.isEmpty()) {
            firstNumber = reduceValue(firstNumber)
            deletedValue = firstNumber
        } else if (operator.isNotEmpty() && secondNumber.isEmpty()) {
            operator = reduceValue(operator)
            deletedValue = operator
        } else if (secondNumber.isNotEmpty()) {
            secondNumber = reduceValue(secondNumber)
            deletedValue = secondNumber
        }
        return deletedValue
    }

    override fun isOperationCompatible(): Boolean {
        return (firstNumber.isNotEmpty()) && (secondNumber.isNotEmpty()) &&
                (firstNumber != ZERO_DOT) && (secondNumber != ZERO_DOT) &&
                (operator.isNotEmpty())
    }

    private fun reduceValue(value: String): String {
        var toReduce = value
        if (toReduce.isNotEmpty()) {
            toReduce = toReduce.substring(ZERO_INT, value.length - ONE_INT)
        }
        return toReduce
    }
}
