package com.torr.coroutinesplayground

import android.util.Log
import kotlinx.android.synthetic.main.fragment_example.*
import kotlinx.android.synthetic.main.fragment_example.view.*
import kotlinx.coroutines.*

class Example_12_Async : BaseFragment(), CoroutineScope by MainScope() {

    override fun startRequests() {
        launch {
            showLoading(loginRequestView)
            val user = login()
            showSuccess(loginRequestView)

            showLoading(friendsRequestView)
            val friendsDeferred = async {
                loadFriends(user)
            }

            showLoading(postsRequestView)
            val postsDeferred = async {
                loadPosts(user)
            }

            val friends = friendsDeferred.await()
            val posts = postsDeferred.await()

            showSuccess(friendsRequestView)
            showSuccess(postsRequestView)

            doSomething(friends, posts)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]?.cancel()
    }

    suspend fun login(): User {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("Kotlin Meetup", "User authenticated on thread ${Thread.currentThread()}")
        }
        return User()
    }

    suspend fun loadFriends(user: User): List<User> {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("Kotlin Meetup", "Friends loaded on thread ${Thread.currentThread()}")
        }
        return listOf()
    }

    suspend fun loadPosts(user: User): List<Any> {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("Kotlin Meetup", "Posts loaded on thread ${Thread.currentThread()}")
        }
        return listOf()
    }
}
