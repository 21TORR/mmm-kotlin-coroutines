package com.torr.coroutinesplayground

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import kotlinx.android.synthetic.main.fragment_example.*

@SuppressLint("StaticFieldLeak")
class Example_13_AsyncTask : BaseFragment() {

    override fun onGoClicked() {
        object : RequestTask<User>(loginRequestView) {
            override fun doInBackground(vararg params: Unit?) = login()

            override fun onPostExecute(user: User) {
                if (activity != null && !activity?.isFinishing!!) {
                    super.onPostExecute(user)

                    object : RequestTask<List<User>>(friendsRequestView) {
                        override fun doInBackground(vararg params: Unit?) = loadFriends(user)

                        override fun onPostExecute(friends: List<User>) {
                            if (activity != null && !activity?.isFinishing!!) {
                                super.onPostExecute(friends)

                                object : RequestTask<List<Any>>(postsRequestView) {
                                    override fun doInBackground(vararg params: Unit?) = loadPosts(user)

                                    override fun onPostExecute(posts: List<Any>) {
                                        if (activity != null && !activity?.isFinishing!!) {
                                            super.onPostExecute(posts)

                                            doSomething(friends, posts)
                                        }
                                    }
                                }.execute()
                            }
                        }
                    }.execute()
                }
            }
        }.execute()
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

    abstract inner class RequestTask<T>(val loadSuccessTextView: LoadSuccessTextView) : AsyncTask<Unit, Unit, T>() {
        override fun onPreExecute() {
            showLoading(loadSuccessTextView)
        }

        override fun onPostExecute(result: T) {
            showSuccess(loadSuccessTextView)
        }
    }
}
