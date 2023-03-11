package io.github.aloussse.calculator

enum class TokenType {
    Plus,
    Minus,
    Divide,
    Multiply,
    Number
}

data class Token(val type: TokenType, val lexeme: String) {
}