package io.github.aloussse.calculator

open class Expr

data class BinaryExpr(
    val lhs: Expr,
    val op: TokenType,
    val rhs: Expr
) : Expr()

data class NumberExpr(
    val value: Double
) : Expr()