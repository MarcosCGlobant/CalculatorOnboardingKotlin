package com.globant.calculatoronboardingkotlin

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.mvp.presenter.CalculatorPresenter
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDING_BY_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.INPUTS_ARE_FULL
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_SIX
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.ONE_AND_DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.OPERATOR_WITH_NO_NUMBER
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.PLUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.TOO_MANY_DOTS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.TOO_MANY_OPERATORS
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class CalculatorPresenterTest {

    private val mockedModel: CalculatorContracts.Model = mock()
    private val mockedView: CalculatorContracts.View = mock()
    private val presenter: CalculatorContracts.Presenter = CalculatorPresenter(mockedModel, mockedView)

    @Test
    fun `on clear button pressed, clear the model and the labels`() {
        presenter.onClearButtonPressed()

        verify(mockedModel).clear()
        verify(mockedView).showInputPressed(EMPTY_STRING)
        verify(mockedView).showResult(EMPTY_STRING)
    }

    @Test
    fun `on delete button pressed, delete the value and show the changed input`() {
        whenever(mockedModel.deleteValue()).doReturn(NUMBER_SIX)

        presenter.onDeleteCurrentNumberButtonPressed()

        verify(mockedModel).deleteValue()
        verify(mockedView).showInputPressed(NUMBER_SIX)
    }

    @Test
    fun `on action button pressed, save operator on the model and show the input`() {
        whenever(mockedModel.addOperator(PLUS)).doReturn(PLUS)

        presenter.onActionButtonPressed(PLUS)

        verify(mockedModel).addOperator(PLUS)
        verify(mockedView).showInputPressed(PLUS)
    }

    @Test
    fun `on action button pressed, with already an operator, show error "TOO MANY OPERATORS"`() {
        whenever(mockedModel.addOperator(PLUS)).doReturn(TOO_MANY_OPERATORS)

        presenter.onActionButtonPressed(PLUS)

        verify(mockedModel).addOperator(PLUS)
        verify(mockedView).showTooManyOperatorsError()
    }

    @Test
    fun `on action button pressed, without the first number, show the error "OPERATOR WITH NO NUMBER"`() {
        whenever(mockedModel.addOperator(PLUS)).doReturn(OPERATOR_WITH_NO_NUMBER)

        presenter.onActionButtonPressed(PLUS)

        verify(mockedModel).addOperator(PLUS)
        verify(mockedView).showOperatorWithNoNumberError()
    }

    @Test
    fun `on action button pressed, with all the inputs full, show the error "INPUTS ARE FULL"`() {
        whenever(mockedModel.addOperator(PLUS)).doReturn(INPUTS_ARE_FULL)

        presenter.onActionButtonPressed(PLUS)

        verify(mockedModel).addOperator(PLUS)
        verify(mockedView).showInputsAreFullError()
    }

    @Test
    fun `on equals button pressed, if operation is compatible, show the result`() {
        whenever(mockedModel.isOperationCompatible()).doReturn(TRUE)
        whenever(mockedModel.calculateResult()).doReturn(NUMBER_SIX)

        presenter.onEqualsButtonPressed()

        verify(mockedModel).isOperationCompatible()
        verify(mockedModel).calculateResult()
        verify(mockedView).showResult(NUMBER_SIX)
    }

    @Test
    fun `on equals button pressed if operation is compatible, but result is "DIVIDING BY ZERO", show error "DIVIDING BY ZERO"`() {
        whenever(mockedModel.isOperationCompatible()).doReturn(TRUE)
        whenever(mockedModel.calculateResult()).doReturn(DIVIDING_BY_ZERO)

        presenter.onEqualsButtonPressed()

        verify(mockedModel).isOperationCompatible()
        verify(mockedModel).calculateResult()
        verify(mockedView).showDivideByZeroError()
    }

    @Test
    fun `on equals button pressed, if operation is incompatible, show the result`() {
        whenever(mockedModel.isOperationCompatible()).doReturn(FALSE)

        presenter.onEqualsButtonPressed()

        verify(mockedModel).isOperationCompatible()
        verify(mockedView).showInvalidOperationError()
    }

    @Test
    fun `on number button pressed, save number on the model and show the input`() {
        whenever(mockedModel.appendNumber(NUMBER_SIX)).doReturn(NUMBER_SIX)

        presenter.onNumberButtonPressed(NUMBER_SIX)

        verify(mockedModel).appendNumber(NUMBER_SIX)
        verify(mockedView).showInputPressed(NUMBER_SIX)
    }

    @Test
    fun `on dot button pressed, append the dot on the number and show the input`() {
        whenever(mockedModel.appendDot(DOT)).doReturn(ONE_AND_DOT)

        presenter.onDotButtonPressed(DOT)

        verify(mockedModel).appendDot(DOT)
        verify(mockedView).showInputPressed(ONE_AND_DOT)
    }

    @Test
    fun `on dot button pressed, try to append another dot on a number with a dot and show the error "TOO MANY DOTS`() {
        whenever(mockedModel.appendDot(DOT)).doReturn(TOO_MANY_DOTS)

        presenter.onDotButtonPressed(DOT)

        verify(mockedModel).appendDot(DOT)
        verify(mockedView).showTooManyDotsError()
    }
}