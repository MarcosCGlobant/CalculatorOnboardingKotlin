package com.globant.calculatoronboardingkotlin

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.mvp.model.CalculatorModel
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDING_BY_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.INPUTS_ARE_FULL
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MINUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MULTIPLY
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_FOUR
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ONE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_THIRTY
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_THREE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_TWO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ONE_AND_DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.OPERATOR_WITH_NO_NUMBER
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.PLUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.THREE_AND_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.TOO_MANY_DOTS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.TOO_MANY_OPERATORS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ZERO_DOT
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Boolean.TRUE

class CalculatorModelTest {

    private val model: CalculatorContracts.Model = CalculatorModel()

    @Test
    fun `without operator and second number, save the first number`() {
        model.appendNumber(NUMBER_THREE)
        val appendNumberReturn = model.appendNumber(NUMBER_ZERO)

        assertEquals(NUMBER_THIRTY, appendNumberReturn)
    }

    @Test
    fun `with operator PLUS, first and second number, calculate the result`() {
        model.appendNumber(NUMBER_ONE)
        model.addOperator(PLUS)
        model.appendNumber(NUMBER_TWO)
        val calculateResultReturn = model.calculateResult()

        assertEquals(THREE_AND_ZERO, calculateResultReturn)
    }

    @Test
    fun `with operator MINUS, first and second number, calculate the result`() {
        model.appendNumber(NUMBER_FOUR)
        model.addOperator(MINUS)
        model.appendNumber(NUMBER_ONE)
        val calculateResultReturn = model.calculateResult()

        assertEquals(THREE_AND_ZERO, calculateResultReturn)
    }

    @Test
    fun `with operator MULTIPLY, first and second number, calculate the result`() {
        model.appendNumber(NUMBER_THREE)
        model.addOperator(MULTIPLY)
        model.appendNumber(NUMBER_ONE)
        val calculateResultReturn = model.calculateResult()

        assertEquals(THREE_AND_ZERO, calculateResultReturn)
    }

    @Test
    fun `with operator DIVIDE, first and second number, calculate the result`() {
        model.appendNumber(NUMBER_THREE)
        model.addOperator(DIVIDE)
        model.appendNumber(NUMBER_ONE)
        val calculateResultReturn = model.calculateResult()

        assertEquals(THREE_AND_ZERO, calculateResultReturn)
    }

    @Test
    fun `with operator DIVIDE, first and second number (a zero), calculate (error)`() {
        model.appendNumber(NUMBER_ONE)
        model.addOperator(DIVIDE)
        model.appendNumber(NUMBER_ZERO)
        val calculateResultReturn = model.calculateResult()

        assertEquals(DIVIDING_BY_ZERO, calculateResultReturn)
    }

    @Test
    fun `without first, second number or operator, append a dot on the first number`() {
        val appendedDotReturn = model.appendDot(DOT)

        assertEquals(ZERO_DOT, appendedDotReturn)
    }

    @Test
    fun `with first number and without second number or operator, append a dot`() {
        model.appendNumber(NUMBER_ONE)
        val appendDotReturn = model.appendDot(DOT)

        assertEquals(ONE_AND_DOT, appendDotReturn)
    }

    @Test
    fun `with first number with dot, and without second number or operator, try to append a dot (error)`() {
        model.appendNumber(ONE_AND_DOT)
        val appendDotReturn = model.appendDot(DOT)

        assertEquals(TOO_MANY_DOTS, appendDotReturn)
    }

    @Test
    fun `with first number and operator, and without second number, append a dot`() {
        model.appendNumber(NUMBER_ONE)
        model.addOperator(PLUS)
        val appendDotReturn = model.appendDot(DOT)

        assertEquals(ZERO_DOT, appendDotReturn)
    }

    @Test
    fun `with first, second number and operator, append a dot on the second number`() {
        model.appendNumber(NUMBER_ONE)
        model.addOperator(PLUS)
        model.appendNumber(NUMBER_ONE)
        val appendDotReturn = model.appendDot(DOT)

        assertEquals(ONE_AND_DOT, appendDotReturn)
    }

    @Test
    fun `without first, second number or operator, append a dot on the second number`() {
        model.appendNumber(NUMBER_ONE)
        model.addOperator(PLUS)
        model.appendNumber(ONE_AND_DOT)
        val appendDotReturn = model.appendDot(DOT)

        assertEquals(TOO_MANY_DOTS, appendDotReturn)
    }

    @Test
    fun `with first number and operator, try to add an operator (error)`() {
        model.appendNumber(NUMBER_ONE)
        model.addOperator(PLUS)
        val addOperatorReturn = model.addOperator(MINUS)

        assertEquals(TOO_MANY_OPERATORS, addOperatorReturn)
    }

    @Test
    fun `without first number, try to add an operator (error)"`() {
        val addOperatorReturn = model.addOperator(MINUS)

        assertEquals(OPERATOR_WITH_NO_NUMBER, addOperatorReturn)
    }

    @Test
    fun `with first second number and operator, try to add an operator  (error)`() {
        model.appendNumber(NUMBER_THREE)
        model.addOperator(DIVIDE)
        model.appendNumber(NUMBER_ONE)
        val addOperatorReturn = model.addOperator(MINUS)

        assertEquals(INPUTS_ARE_FULL, addOperatorReturn)
    }

    @Test
    fun `with only first number, reduce first number`() {
        model.appendNumber(NUMBER_THREE)
        model.appendNumber(NUMBER_ZERO)

        val deleteValueReturn = model.deleteValue()

        assertEquals(NUMBER_THREE, deleteValueReturn)
    }

    @Test
    fun `with only first number and operator, delete operator`() {
        model.appendNumber(NUMBER_THREE)
        model.appendNumber(NUMBER_ZERO)
        model.addOperator(PLUS)

        val deleteValueReturn = model.deleteValue()

        assertEquals(EMPTY_STRING, deleteValueReturn)
    }

    @Test
    fun `with first, second number and operator, reduce second number`() {
        model.appendNumber(NUMBER_THREE)
        model.appendNumber(NUMBER_ZERO)
        model.addOperator(PLUS)
        model.appendNumber(NUMBER_THREE)
        model.appendNumber(NUMBER_ZERO)

        val deleteValueReturn = model.deleteValue()

        assertEquals(NUMBER_THREE, deleteValueReturn)
    }

    @Test
    fun `check the return of isOperationCompatible if the values are wrong`() {
        model.appendNumber(NUMBER_THREE)
        model.appendNumber(NUMBER_ZERO)
        model.addOperator(PLUS)
        model.appendNumber(NUMBER_THREE)
        model.appendNumber(NUMBER_ZERO)

        val isOperationCompatibleReturn = model.isOperationCompatible()

        assertEquals(TRUE, isOperationCompatibleReturn)
    }
}