package com.globant.calculatoronboardingkotlin.mvp.model

import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING


class CalculatorModel {

    var firstNumber: String? = EMPTY_STRING

    var secondNumber: String? = EMPTY_STRING

    var operator: String? = EMPTY_STRING

    fun clear() {
        firstNumber = EMPTY_STRING

        secondNumber = EMPTY_STRING

        operator = EMPTY_STRING
    }

}
