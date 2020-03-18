package com.example.calculatoronboardingkotlin

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

    private val model : CalculatorModel = CalculatorModel()
    private val mockedView : CalculatorView = mock(CalculatorView::class.java)
    private val presenter : CalculatorPresenter = CalculatorPresenter(model, mockedView)

    @Test
    fun onClearButtonPressedClearTheModelAndTheView(){
        presenter.onClearButtonPressed()

        verify(mockedView).showOperationPressed(EMPTY_STRING)
        verify(mockedView).showResult(EMPTY_STRING)

        assertEquals(EMPTY_STRING, model.firstNumber)
        assertEquals(EMPTY_STRING, model.secondNumber)
        assertEquals(EMPTY_STRING, model.operator)
    }

    @Test
    fun onActionButtonPressedSaveValueOnModelAndShowInput(){
        presenter.onActionButtonPressed(MINUS)

        verify(mockedView).showOperationPressed(MINUS)

        assertEquals(MINUS, model.operator)
    }

    @Test
    fun onNumberButtonPressedSaveValueOnMOdelAndShowInput(){
        presenter.onNumberButtonPressed(NUMBER_ONE)

        verify(mockedView).showNumberPressed(NUMBER_ONE)

        assertEquals(NUMBER_ONE, model.firstNumber)
    }

}
