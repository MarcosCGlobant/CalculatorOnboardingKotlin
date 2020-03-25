package com.globant.calculatoronboardingkotlin

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.mvp.model.CalculatorModel
import com.globant.calculatoronboardingkotlin.mvp.presenter.CalculatorPresenter
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MINUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MULTIPLY
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ONE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_SIX
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_THIRTY
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_THREE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_TWO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ONE_AND_DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.PLUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.THREE_AND_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ZERO_DOT
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.assertEquals

class CalculatorPresenterTest {

    private val model: CalculatorContracts.Model = CalculatorModel()
    private val mockedView: CalculatorContracts.View = mock()
    private val presenter: CalculatorContracts.Presenter = CalculatorPresenter(model, mockedView)

    @Test
    fun `on clear button pressed with operator, first and second number loaded, clear model`() {
        model.firstNumber = NUMBER_ONE
        model.operator = PLUS
        model.secondNumber = NUMBER_TWO

        presenter.onClearButtonPressed()

        verify(mockedView).showInputPressed(EMPTY_STRING)
        verify(mockedView).showResult(EMPTY_STRING)

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on action button pressed with only the first number, show operator`() {
        model.firstNumber = NUMBER_ONE

        presenter.onActionButtonPressed(MINUS)

        verify(mockedView).showInputPressed(MINUS)

        assertEquals(MINUS, model.operator)
    }

    @Test
    fun `on action button pressed with first number and operator, show error "TOO MANY OPERATORS"`() {
        model.firstNumber = NUMBER_ONE
        model.operator = PLUS

        presenter.onActionButtonPressed(MINUS)

        verify(mockedView).showTooManyOperatorsError()

        assertEquals(PLUS, model.operator)
    }

    @Test
    fun `on action button pressed without first number, show error "OPERATOR WITH NO NUMBER"`() {
        presenter.onActionButtonPressed(MINUS)

        verify(mockedView).showOperatorWithNoNumberError()

        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on action button pressed with first, second number and operator, save the result on the first number, empty the second number and save the new operator`() {
        model.firstNumber = NUMBER_ONE
        model.operator = PLUS
        model.secondNumber = NUMBER_TWO

        presenter.onActionButtonPressed(MINUS)

        verify(mockedView).showResult(THREE_AND_ZERO)
        verify(mockedView).showInputPressed(MINUS)

        assertEquals(THREE_AND_ZERO, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(MINUS, model.operator)
    }

    @Test
    fun `on equals button pressed with both numbers and operator, show result and clear model`() {
        model.firstNumber = NUMBER_ONE
        model.operator = PLUS
        model.secondNumber = NUMBER_TWO

        presenter.onEqualsButtonPressed()

        verify(mockedView).showResult(THREE_AND_ZERO)

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on equals button pressed with just the first number, show result and clear model`() {
        model.firstNumber = NUMBER_ONE

        presenter.onEqualsButtonPressed()

        verify(mockedView).showResult(NUMBER_ONE)

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on equals button pressed with an invalid operation (dots as numbers), show error "INVALID OPERATION" and clear model`() {
        model.firstNumber = ZERO_DOT
        model.operator = PLUS
        model.secondNumber = ZERO_DOT


        presenter.onEqualsButtonPressed()

        verify(mockedView).showInvalidOperationError()

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on number button pressed without operator and second number, save on first number and show`() {
        presenter.onNumberButtonPressed(NUMBER_ONE)

        verify(mockedView).showInputPressed(NUMBER_ONE)

        assertEquals(NUMBER_ONE, model.firstNumber)
    }

    @Test
    fun `on number button pressed with operator and first number, save on second number and show`() {
        model.firstNumber = NUMBER_ONE
        model.operator = PLUS

        presenter.onNumberButtonPressed(NUMBER_ONE)

        verify(mockedView).showInputPressed(NUMBER_ONE)

        assertEquals(NUMBER_ONE, model.firstNumber)
        assertEquals(NUMBER_ONE, model.secondNumber)
        assertEquals(PLUS, model.operator)
    }

    @Test
    fun `on dot button pressed without first, second number or operator, save on first number and show`() {
        presenter.onDotButtonPressed(DOT)

        verify(mockedView).showInputPressed(ZERO_DOT)

        assertEquals(ZERO_DOT, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on dot button pressed with first number and without second number or operator, add to first number and show`() {
        model.firstNumber = NUMBER_ONE

        presenter.onDotButtonPressed(DOT)

        verify(mockedView).showInputPressed(ONE_AND_DOT)

        assertEquals(ONE_AND_DOT, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on dot button pressed with first number with dot, and without second number or operator, show error "TOO MANY DOTS"`() {
        model.firstNumber = ONE_AND_DOT

        presenter.onDotButtonPressed(DOT)

        verify(mockedView).showTooManyDotsError()

        assertEquals(ONE_AND_DOT, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on dot button pressed with first number and operator, and without second number, save on second number and show`() {
        model.firstNumber = NUMBER_ONE
        model.operator = PLUS

        presenter.onDotButtonPressed(DOT)

        verify(mockedView).showInputPressed(ZERO_DOT)

        assertEquals(NUMBER_ONE, model.firstNumber)
        assertEquals(ZERO_DOT, model.secondNumber)
        assertEquals(PLUS, model.operator)
    }

    @Test
    fun `on dot button pressed with first number and operator, and with second number, add to second number and show`() {
        model.firstNumber = NUMBER_ONE
        model.operator = PLUS
        model.secondNumber = NUMBER_ONE

        presenter.onDotButtonPressed(DOT)

        verify(mockedView).showInputPressed(ONE_AND_DOT)

        assertEquals(NUMBER_ONE, model.firstNumber)
        assertEquals(ONE_AND_DOT, model.secondNumber)
        assertEquals(PLUS, model.operator)
    }

    @Test
    fun `on dot button pressed with first number and operator, and second number with dot, show error "TOO MANY DOTS"`() {
        model.firstNumber = NUMBER_ONE
        model.operator = PLUS
        model.secondNumber = ONE_AND_DOT

        presenter.onDotButtonPressed(DOT)

        verify(mockedView).showTooManyDotsError()

        assertEquals(NUMBER_ONE, model.firstNumber)
        assertEquals(ONE_AND_DOT, model.secondNumber)
        assertEquals(PLUS, model.operator)
    }

    @Test
    fun `on equals button pressed with both numbers and operator MINUS, show result and clear model`() {
        model.firstNumber = NUMBER_SIX
        model.operator = MINUS
        model.secondNumber = NUMBER_THREE

        presenter.onEqualsButtonPressed()

        verify(mockedView).showResult(THREE_AND_ZERO)

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on equals button pressed with both numbers and operator MULTIPLY, show result and clear model`() {
        model.firstNumber = NUMBER_THREE
        model.operator = MULTIPLY
        model.secondNumber = NUMBER_ONE

        presenter.onEqualsButtonPressed()

        verify(mockedView).showResult(THREE_AND_ZERO)

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on equals button pressed with both numbers and operator DIVIDE, show result and clear model`() {
        model.firstNumber = NUMBER_THREE
        model.operator = DIVIDE
        model.secondNumber = NUMBER_ONE

        presenter.onEqualsButtonPressed()

        verify(mockedView).showResult(THREE_AND_ZERO)

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on equals button pressed trying to divide by zero, show error "DIVIDE BY ZERO" and clear model`() {
        model.firstNumber = NUMBER_ONE
        model.operator = DIVIDE
        model.secondNumber = NUMBER_ZERO

        presenter.onEqualsButtonPressed()

        verify(mockedView).showDivideByZeroError()

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on delete button pressed with only first number, reduce first number and show`() {
        model.firstNumber = NUMBER_THIRTY

        presenter.onDeleteCurrentNumberButtonPressed()

        verify(mockedView).showInputPressed(NUMBER_THREE)

        assertEquals(NUMBER_THREE, model.firstNumber)
    }

    @Test
    fun `on delete button pressed with only first number and operator, delete operator and show`() {
        model.firstNumber = NUMBER_THIRTY
        model.operator = PLUS

        presenter.onDeleteCurrentNumberButtonPressed()

        verify(mockedView).showInputPressed(EMPTY_STRING)

        assertEquals(NUMBER_THIRTY, model.firstNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun `on delete button pressed with first, second number and operator, reduce second number and show`() {
        model.firstNumber = NUMBER_THIRTY
        model.operator = PLUS
        model.secondNumber = NUMBER_THIRTY

        presenter.onDeleteCurrentNumberButtonPressed()

        verify(mockedView).showInputPressed(NUMBER_THREE)

        assertEquals(NUMBER_THIRTY, model.firstNumber)
        assertEquals(NUMBER_THREE, model.secondNumber)
        assertEquals(PLUS, model.operator)
    }
}
