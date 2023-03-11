package io.github.aloussse.calculator

class Evaluator(source: String) {
    private val ast: Expr? = Parser(source).parse()

    fun eval(): Double? {
        if (ast == null) {
            return null
        }

        return eval(ast)
    }

    private fun eval(ast: Expr): Double? {
        return when (ast) {
            is BinaryExpr -> {
                val rhs = eval(ast.rhs)
                val lhs = eval(ast.lhs)
                if (rhs != null && lhs != null) {
                    when (ast.op) {
                        TokenType.Divide -> lhs / rhs
                        TokenType.Multiply -> lhs * rhs
                        TokenType.Plus -> lhs + rhs
                        TokenType.Minus -> lhs - rhs
                        else -> null
                    }
                } else {
                    null
                }
            }
            is NumberExpr -> ast.value
            else -> null
        }
    }
}