package com.torr.coroutinesplayground

import android.util.Log

class Example_01_Blocking_No_Ui : BaseFragment() {

    override fun startRequests() {
        val user = login()
        val friends = loadFriends(user)
        val posts = loadPosts(user)
        doSomething(friends, posts)
    }

    fun login(): User {
        Thread.sleep(2000)
        Log.d("Kotlin Meetup", "User authenticated on thread ${Thread.currentThread()}")
        return User()
    }

    fun loadFriends(user: User): List<User> {
        Thread.sleep(2000)
        Log.d("Kotlin Meetup", "Friends loaded on thread ${Thread.currentThread()}")
        return listOf()
    }

    fun loadPosts(user: User): List<Any> {
        Thread.sleep(2000)
        Log.d("Kotlin Meetup", "Posts loaded on thread ${Thread.currentThread()}")
        return listOf()
    }
}
