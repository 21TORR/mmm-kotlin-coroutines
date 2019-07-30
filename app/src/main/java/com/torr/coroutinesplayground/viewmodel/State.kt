package com.torr.coroutinesplayground.viewmodel

sealed class State<T>(val value: T? = null) {
    class Loading<T> : State<T>()
    class Success<T>(value: T) : State<T>(value)
}