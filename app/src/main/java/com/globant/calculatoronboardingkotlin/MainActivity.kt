package com.globant.calculatoronboardingkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatoronboardingkotlin.R
import com.globant.calculatoronboardingkotlin.mvp.model.CalculatorModel
import com.globant.calculatoronboardingkotlin.mvp.presenter.CalculatorPresenter
import com.globant.calculatoronboardingkotlin.mvp.view.CalculatorView
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DIVIDE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.DOT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MINUS
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.MULTIPLY
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_EIGHT
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_FIVE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_FOUR
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_NINE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ONE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_SEVEN
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_SIX
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_THREE
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_TWO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.NUMBER_ZERO
import com.globant.calculatoronboardingkotlin.utils.Constants.Companion.PLUS
import kotlinx.android.synthetic.main.activity_main.button_main_add
import kotlinx.android.synthetic.main.activity_main.button_main_clear
import kotlinx.android.synthetic.main.activity_main.button_main_delete_current_number
import kotlinx.android.synthetic.main.activity_main.button_main_divide
import kotlinx.android.synthetic.main.activity_main.button_main_dot
import kotlinx.android.synthetic.main.activity_main.button_main_equals
import kotlinx.android.synthetic.main.activity_main.button_main_multiply
import kotlinx.android.synthetic.main.activity_main.button_main_number_eight
import kotlinx.android.synthetic.main.activity_main.button_main_number_five
import kotlinx.android.synthetic.main.activity_main.button_main_number_four
import kotlinx.android.synthetic.main.activity_main.button_main_number_nine
import kotlinx.android.synthetic.main.activity_main.button_main_number_one
import kotlinx.android.synthetic.main.activity_main.button_main_number_seven
import kotlinx.android.synthetic.main.activity_main.button_main_number_six
import kotlinx.android.synthetic.main.activity_main.button_main_number_three
import kotlinx.android.synthetic.main.activity_main.button_main_number_two
import kotlinx.android.synthetic.main.activity_main.button_main_number_zero
import kotlinx.android.synthetic.main.activity_main.button_main_subtraction

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: CalculatorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = CalculatorPresenter(CalculatorModel(), CalculatorView(this))
        initOnClickListeners()
    }

    private fun initOnClickListeners() {

        button_main_clear.setOnClickListener {
            presenter.onClearButtonPressed()
        }
        button_main_delete_current_number.setOnClickListener {
            presenter.onDeleteCurrentNumberButtonPressed()
        }
        button_main_equals.setOnClickListener {
            presenter.onEqualsButtonPressed()
        }
        button_main_add.setOnClickListener {
            presenter.onActionButtonPressed(PLUS)
        }
        button_main_subtraction.setOnClickListener {
            presenter.onActionButtonPressed(MINUS)
        }
        button_main_multiply.setOnClickListener {
            presenter.onActionButtonPressed(MULTIPLY)
        }
        button_main_divide.setOnClickListener {
            presenter.onActionButtonPressed(DIVIDE)
        }
        button_main_number_one.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_ONE)
        }
        button_main_number_two.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_TWO)
        }
        button_main_number_three.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_THREE)
        }
        button_main_number_four.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_FOUR)
        }
        button_main_number_five.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_FIVE)
        }
        button_main_number_six.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_SIX)
        }
        button_main_number_seven.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_SEVEN)
        }
        button_main_number_eight.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_EIGHT)
        }
        button_main_number_nine.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_NINE)
        }
        button_main_number_zero.setOnClickListener {
            presenter.onNumberButtonPressed(NUMBER_ZERO)
        }
        button_main_dot.setOnClickListener {
            presenter.onNumberButtonPressed(DOT)
        }
    }
}

