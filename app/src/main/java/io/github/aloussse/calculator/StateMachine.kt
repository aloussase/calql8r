package io.github.aloussse.calculator

interface StateMachine<T> {
    val state: T

    fun changeState(newState: T)
}