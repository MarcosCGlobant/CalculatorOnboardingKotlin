package com.globant.calculatoronboardingkotlin.mvp.model

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
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
}
