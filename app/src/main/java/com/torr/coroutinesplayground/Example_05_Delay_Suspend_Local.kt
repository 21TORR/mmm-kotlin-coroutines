package com.torr.coroutinesplayground

import android.util.Log
import kotlinx.android.synthetic.main.fragment_example.*
import kotlinx.android.synthetic.main.fragment_example.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Example_05_Delay_Suspend_Local : BaseFragment() {

    override fun onGoClicked() {
        CoroutineScope(Dispatchers.Main).launch {
            showLoading(loginRequestView)
            val user = login()
            showSuccess(loginRequestView)

            showLoading(friendsRequestView)
            val friends = loadFriends(user)
            showSuccess(friendsRequestView)

            showLoading(postsRequestView)
            val posts = loadPosts(user)
            showSuccess(postsRequestView)

            doSomething(friends, posts)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("asdf", "aaaaaaaa")
    }

    suspend fun login(): User {
        delay(2000)
        Log.d("kotlinCoroutines", "User authenticated on thread ${Thread.currentThread()}")
        return User()
    }

    suspend fun loadFriends(user: User): List<User> {
        delay(2000)
        Log.d("kotlinCoroutines", "Friends loaded on thread ${Thread.currentThread()}")
        return listOf()
    }

    suspend fun loadPosts(user: User): List<Any> {
        delay(2000)
        Log.d("kotlinCoroutines", "Posts loaded on thread ${Thread.currentThread()}")
        return listOf()
    }
}
