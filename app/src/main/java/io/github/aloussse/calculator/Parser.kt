package io.github.aloussse.calculator

class Parser(source: String) {
    private var current = 0
    private val tokens: List<Token> = Tokenizer(source).tokenize()

    fun parse(): Expr? {
        return parseExpr()
    }

    private fun parseExpr(): Expr? {
        var lhs = parseTerm() ?: return null
        var currentToken = peek()

        while (currentToken != null
            && (currentToken.type == TokenType.Plus || currentToken.type == TokenType.Minus)
        ) {
            advance()
            val rhs = parseTerm()

            if (rhs != null) {
                lhs = BinaryExpr(lhs, currentToken.type, rhs)
            }

            currentToken = peek()
        }

        return lhs
    }

    private fun parseTerm(): Expr? {
        var lhs = parseFactor() ?: return null
        var currentToken = peek()

        while (currentToken != null &&
            (currentToken.type == TokenType.Multiply || currentToken.type == TokenType.Divide)
        ) {
            advance()
            val rhs = parseFactor()

            if (rhs != null) {
                lhs = BinaryExpr(lhs, currentToken.type, rhs)
            }

            currentToken = peek()
        }

        return lhs
    }

    private fun parseFactor(): Expr? {
        val currentToken = advance()
        if (currentToken != null && currentToken.type == TokenType.Number) {
            return NumberExpr(currentToken.lexeme.toDouble())
        }
        return null
    }

    private fun advance(): Token? {
        if (current < tokens.size) {
            return tokens.get(current++)
        }
        return null
    }

    private fun peek(): Token? {
        if (current < tokens.size) {
            return tokens.get(current)
        }
        return null
    }

}