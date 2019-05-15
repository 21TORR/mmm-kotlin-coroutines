package com.torr.coroutinesplayground

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_example.*

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_example, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        go.setOnClickListener {
            startRequests()
        }
    }

    fun showLoading(loadSuccessTextView: LoadSuccessTextView) {
        loadSuccessTextView.state = LoadSuccessTextView.State.LOADING
    }

    fun showSuccess(loadSuccessTextView: LoadSuccessTextView) {
        loadSuccessTextView.state = LoadSuccessTextView.State.SUCCESS
    }

    fun doSomething(friends: List<User>, posts: List<Any>) {
        Log.d("MMM", "friends & posts loaded")
    }

    abstract fun startRequests()
}