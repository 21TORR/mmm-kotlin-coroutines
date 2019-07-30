package com.torr.coroutinesplayground

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.torr.coroutinesplayground.viewmodel.KViewModel
import com.torr.coroutinesplayground.viewmodel.State
import kotlinx.android.synthetic.main.fragment_example.*
import kotlinx.coroutines.*

class Example_15_ViewModel : BaseFragment() {

    val viewModel by viewModels<KViewModel>()

    override fun onGoClicked() {
        viewModel.loadUser()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.userState.observe(viewLifecycleOwner, Observer {
            handleState(it, loginRequestView)
        })

        viewModel.friendsState.observe(viewLifecycleOwner, Observer {
            handleState(it, friendsRequestView)
        })

        viewModel.postsState.observe(viewLifecycleOwner, Observer {
            handleState(it, postsRequestView)
        })
    }

    private fun <T> handleState(state: State<T>, loadSuccessTextView: LoadSuccessTextView) {
        when (state) {
            is State.Loading -> showLoading(loadSuccessTextView)
            is State.Success -> showSuccess(loadSuccessTextView)
        }
    }
}
