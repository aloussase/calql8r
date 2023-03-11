package io.github.aloussse.calculator

class DotCalculatorState(private val stateMachine: StateMachine<CalculatorState>) :
    CalculatorState {

    override fun onDigit(currentInput: String, digit: String): String {
        return currentInput + digit
    }

    override fun onOperator(currentInput: String, operator: String): String {
        stateMachine.changeState(OperatorCalculatorState(stateMachine))
        return currentInput + operator
    }

    override fun onDot(currentInput: String): String {
        return currentInput
    }

    override fun onClear(currentInput: String): String {
        stateMachine.changeState(NormalCalculatorState(stateMachine))
        return ""
    }

    override fun onEqual(currentInput: String): String {
        stateMachine.changeState(NormalCalculatorState(stateMachine))

        val result = Evaluator(currentInput).eval()

        if (result != null) {
            return result.toString()
        }

        return currentInput
    }
}