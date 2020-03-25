package com.globant.calculatoronboardingkotlin.mvp.model

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.utils.Constants
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDING_BY_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING

class CalculatorModel : CalculatorContracts.Model {

    override var firstNumber: String = EMPTY_STRING
    override var secondNumber: String = EMPTY_STRING
    override var operator: String = EMPTY_STRING

    override fun clear() {
        firstNumber = EMPTY_STRING
        secondNumber = EMPTY_STRING
        operator = EMPTY_STRING
    }

    override fun calculateResult(): String {
        val firstNumber = firstNumber.toDouble()
        val secondNumber = secondNumber.toDouble()
        var result: Double = Constants.ZERO_DOUBLE
        when (operator) {
            Constants.PLUS -> result = firstNumber + secondNumber
            Constants.MINUS -> result = firstNumber - secondNumber
            Constants.MULTIPLY -> result = firstNumber * secondNumber
            Constants.DIVIDE -> if (secondNumber == Constants.ZERO_DOUBLE) {
                return DIVIDING_BY_ZERO
            } else {
                result = firstNumber / secondNumber
            }
        }
        return result.toString()
    }
}
