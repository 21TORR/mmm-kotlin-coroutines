package com.torr.coroutinesplayground

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import kotlinx.android.synthetic.main.fragment_example.*
import kotlinx.coroutines.*

class Example_14_LifecycleScope : BaseFragment() {

    override fun onGoClicked() {
        lifecycleScope.launch {
            showLoading(loginRequestView)
            val user = login()
            showSuccess(loginRequestView)

            showLoading(friendsRequestView)
            val friends = loadFriends(user)
            showSuccess(friendsRequestView)

            showLoading(postsRequestView)
            val posts = loadPosts(user)
            showSuccess(postsRequestView)

            gotoPage2()
        }
    }

    suspend fun gotoPage2() {
        whenStarted {
            Log.d("kotlinCoroutines", "Goto page 2")
            fragmentManager?.beginTransaction()!!
                .replace(R.id.fragment_container, Page2Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    suspend fun login(): User {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("kotlinCoroutines", "User authenticated on thread ${Thread.currentThread()}")
        }
        return User()
    }

    suspend fun loadFriends(user: User): List<User> {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("kotlinCoroutines", "Friends loaded on thread ${Thread.currentThread()}")
        }
        return listOf()
    }

    suspend fun loadPosts(user: User): List<Any> {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("kotlinCoroutines", "Posts loaded on thread ${Thread.currentThread()}")
        }
        return listOf()
    }
}
