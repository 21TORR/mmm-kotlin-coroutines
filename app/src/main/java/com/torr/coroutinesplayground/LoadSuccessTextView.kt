package com.torr.coroutinesplayground

import android.animation.AnimatorInflater
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import kotlinx.android.synthetic.main.view_load_success_error.view.*

class LoadSuccessTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var text: String
    set(value) {
        request.text = value
    }
    get() = request.text.toString()

    var state: State = State.IDLE
    set(value) {
        field = value
        updateState(value)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_load_success_error, this, true)
        context.withStyledAttributes(attrs, R.styleable.LoadSuccessTextView) {
            text = getString(R.styleable.LoadSuccessTextView_text)
        }

        stateListAnimator = AnimatorInflater.loadStateListAnimator(context, R.animator.request)
        state = State.IDLE
    }

    private fun updateState(state: State) {
        when(state) {
            State.IDLE -> setStateIdle()
            State.LOADING -> setStateLoading()
            State.SUCCESS -> setStateSuccess()
            State.ERROR -> setStateError()
        }
    }

    private fun setStateIdle() {
        isEnabled = false
        progress.visibility = View.INVISIBLE
        checkbox.visibility = View.INVISIBLE
    }

    private fun setStateLoading() {
        isEnabled = true
        progress.visibility = View.VISIBLE
        checkbox.visibility = View.INVISIBLE
    }

    private fun setStateSuccess() {
        isEnabled = true
        progress.visibility = View.INVISIBLE
        checkbox.visibility = View.VISIBLE
        checkbox.isChecked = true
    }

    private fun setStateError() {
        isEnabled = true
        progress.visibility = View.INVISIBLE
        checkbox.visibility = View.INVISIBLE
    }

    enum class State {
        IDLE, LOADING, SUCCESS, ERROR
    }
}