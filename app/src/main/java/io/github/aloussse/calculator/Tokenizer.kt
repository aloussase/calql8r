package io.github.aloussse.calculator

class Tokenizer(private val source: String) {
    private var current: Int = 0
    private var start: Int = 0

    fun tokenize(): List<Token> {
        val tokens = ArrayList<Token>()

        while (peek() != null) {
            start = current

            when (advance()!!) {
                '+' -> tokens.add(Token(TokenType.Plus, "+"))
                '-' -> tokens.add(Token(TokenType.Minus, "-"))
                'x' -> tokens.add(Token(TokenType.Multiply, "x"))
                '/' -> tokens.add(Token(TokenType.Divide, "/"))
                else -> tokenizeNumber()?.let { token ->
                    tokens.add(token)
                }
            }
        }

        return tokens
    }

    private fun tokenizeNumber(): Token? {
        var currentChar: Char? = peek()

        // Here we don't need to check that we have only one dot in the number because
        // our input state machine already checks for that.

        while (currentChar != null && (currentChar.isDigit() || currentChar == '.')) {
            advance()
            currentChar = peek()
        }

        val end = if (source[current - 1] == '.') current - 1 else current
        val lexeme = source.substring(start, end)

        if (lexeme.isEmpty()) {
            return null
        }

        return Token(TokenType.Number, lexeme)
    }

    private fun peek(): Char? {
        if (current < source.length) {
            return source[current]
        }
        return null
    }

    private fun advance(): Char? {
        if (current < source.length) {
            return source[current++]
        }
        return null
    }
}