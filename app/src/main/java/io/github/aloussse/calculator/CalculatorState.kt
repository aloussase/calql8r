package io.github.aloussse.calculator

interface CalculatorState {
    fun onDigit(currentInput: String, digit: String): String

    fun onOperator(currentInput: String, operator: String): String

    fun onDot(currentInput: String): String

    fun onClear(currentInput: String): String
}