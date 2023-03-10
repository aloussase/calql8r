package io.github.aloussse.calculator

class OperatorCalculatorState(private val stateMachine: StateMachine<CalculatorState>) :
    CalculatorState {
    override fun onDigit(currentInput: String, digit: String): String {
        stateMachine.changeState(NormalCalculatorState(stateMachine))
        return currentInput + digit
    }

    override fun onOperator(currentInput: String, operator: String): String {
        return currentInput
    }

    override fun onDot(currentInput: String): String {
        return currentInput
    }

    override fun onClear(currentInput: String): String {
        stateMachine.changeState(NormalCalculatorState(stateMachine))
        return ""
    }
}