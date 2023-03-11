package io.github.aloussse.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), StateMachine<CalculatorState> {
    private var tvInput: TextView? = null

    private var currentInput: String
        get() = tvInput?.text as String? ?: ""
        set(value) {
            tvInput?.text = value
        }


    override var state: CalculatorState = NormalCalculatorState(this)

    override fun changeState(newState: CalculatorState) {
        state = newState
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

        val digitButtons = listOf<Button>(
            findViewById(R.id.btnZero),
            findViewById(R.id.btnOne),
            findViewById(R.id.btnTwo),
            findViewById(R.id.btnThree),
            findViewById(R.id.btnFour),
            findViewById(R.id.btnFive),
            findViewById(R.id.btnSix),
            findViewById(R.id.btnSeven),
            findViewById(R.id.btnEight),
            findViewById(R.id.btnNine),
        )

        val opButtons = listOf<Button>(
            findViewById(R.id.opDivide),
            findViewById(R.id.opMinus),
            findViewById(R.id.opMultiply)
        )

        digitButtons.forEach { it.setOnClickListener(this::onDigitClicked) }

        opButtons.forEach { it.setOnClickListener(this::onOpClicked) }

        findViewById<Button>(R.id.btnClear).setOnClickListener(this::onClearClicked)
        findViewById<Button>(R.id.btnDot).setOnClickListener(this::onDotClicked)

        findViewById<Button>(R.id.opEq).setOnClickListener(this::onEqualClicked)
    }

    private fun onOpClicked(view: View) {
        val operator: String = (view as Button).text as String
        currentInput = state.onOperator(currentInput, operator)
    }

    private fun onClearClicked(view: View) {
        currentInput = state.onClear(currentInput)
    }

    private fun onDigitClicked(view: View) {
        val digit: String = (view as Button).text as String
        currentInput = state.onDigit(currentInput, digit)
    }

    private fun onDotClicked(view: View) {
        currentInput = state.onDot(currentInput)
    }

    private fun onEqualClicked(view: View) {
        currentInput = state.onEqual(currentInput)
    }
}