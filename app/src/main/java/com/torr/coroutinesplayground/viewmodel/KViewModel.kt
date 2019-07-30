package com.torr.coroutinesplayground.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.torr.coroutinesplayground.User
import kotlinx.coroutines.*

class KViewModel : ViewModel() {
    private val email = MutableLiveData<String>()
    private val userLiveData = MutableLiveData<User>()

    val userState= email.switchMap {
        liveData<State<User>> {
            emit(State.Loading())
            val user = login()
            userLiveData.value = user
            emit(State.Success(user))
        }
    }

    val friendsState = userLiveData.switchMap {
        liveData<State<List<User>>> {
            emit(State.Loading())
            val friends = loadFriends(it)
            emit(State.Success(friends))
        }
    }

    val postsState = userLiveData.switchMap {
        liveData<State<List<Any>>> {
            emit(State.Loading())
            val posts = loadPosts(it)
            emit(State.Success(posts))
        }
    }

    fun loadUser() {
        email.value = "foo@bar.com"
    }

    private suspend fun login(): User {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("kotlinCoroutines", "User authenticated on thread ${Thread.currentThread()}")
        }

        return User()
    }

    private suspend fun loadFriends(user: User): List<User> {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("kotlinCoroutines", "Friends loaded on thread ${Thread.currentThread()}")
        }

        return listOf()
    }

    private suspend fun loadPosts(user: User): List<Any> {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("kotlinCoroutines", "Posts loaded on thread ${Thread.currentThread()}")
        }

        return listOf()
    }
}