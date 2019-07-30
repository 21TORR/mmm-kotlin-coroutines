package com.torr.coroutinesplayground

import android.util.Log
import kotlinx.android.synthetic.main.fragment_example.*
import kotlinx.android.synthetic.main.fragment_example.view.*

class ExampleFragment : BaseFragment() {

    override fun onGoClicked() {
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

    fun login(): User {
        Thread.sleep(2000)
        Log.d("kotlinCoroutines", "User authenticated on thread ${Thread.currentThread()}")
        return User()
    }

    fun loadFriends(user: User): List<User> {
        Thread.sleep(2000)
        Log.d("kotlinCoroutines", "Friends loaded on thread ${Thread.currentThread()}")
        return listOf()
    }

    fun loadPosts(user: User): List<Any> {
        Thread.sleep(2000)
        Log.d("kotlinCoroutines", "Posts loaded on thread ${Thread.currentThread()}")
        return listOf()
    }
}
