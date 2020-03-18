package com.example.calculatoronboardingkotlin

import com.globant.calculatoronboardingkotlin.mvp.contracts.CalculatorContracts
import com.globant.calculatoronboardingkotlin.mvp.model.CalculatorModel
import com.globant.calculatoronboardingkotlin.mvp.presenter.CalculatorPresenter
import com.globant.calculatoronboardingkotlin.mvp.view.CalculatorView
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.EMPTY_STRING
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MINUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ONE
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class CalculatorPresenterTest {

    private val model: CalculatorContracts.Model = CalculatorModel()
    private val mockedView: CalculatorContracts.View = mock(CalculatorView::class.java)
    private val presenter: CalculatorContracts.Presenter = CalculatorPresenter(model, mockedView)

    @Test
    fun onClearButtonPressedClearTheModelAndTheView() {
        presenter.onClearButtonPressed()

        verify(mockedView).showInputPressed(EMPTY_STRING)
        verify(mockedView).showResult(EMPTY_STRING)

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun onActionButtonPressedSaveValueOnModelAndShowInput() {
        presenter.onActionButtonPressed(MINUS)

        verify(mockedView).showInputPressed(MINUS)

        assertEquals(MINUS, model.operator)
    }

    @Test
    fun onNumberButtonPressedSaveValueOnMOdelAndShowInput() {
        presenter.onNumberButtonPressed(NUMBER_ONE)

        verify(mockedView).showInputPressed(NUMBER_ONE)

        assertEquals(NUMBER_ONE, model.firstNumber)
    }

}
