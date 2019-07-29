package com.torr.coroutinesplayground

import android.util.Log
import kotlinx.android.synthetic.main.fragment_example.*
import kotlinx.android.synthetic.main.fragment_example.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class Example_110_Channel_Producers : BaseFragment(), CoroutineScope by MainScope() {

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

            val mediaFiles = editAndRenderMediaFiles()

            uploadMediaFile(mediaFiles)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]?.cancel()
    }

    suspend fun login(): User {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("MMM", "User authenticated on thread ${Thread.currentThread()}")
        }
        return User()
    }

    suspend fun loadFriends(user: User): List<User> {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("MMM", "Friends loaded on thread ${Thread.currentThread()}")
        }
        return listOf()
    }

    suspend fun loadPosts(user: User): List<Any> {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.d("MMM", "Posts loaded on thread ${Thread.currentThread()}")
        }
        return listOf()
    }

    private fun CoroutineScope.editAndRenderMediaFiles() = produce {
        showLoading(fileRenderView)
        withContext(Dispatchers.Default) {
            val catVideo = MediaFile.loadAndRender("Cat.mp4")
            send(catVideo)
            val musicVideo = MediaFile.loadAndRender("MusicVideo.mp4")
            send(musicVideo)
            val meetupTalk = MediaFile.loadAndRender("Meetup.mp4")
            send(meetupTalk)
        }
        showSuccess(fileRenderView)
    }

    private fun CoroutineScope.uploadMediaFile(mediaFiles: ReceiveChannel<MediaFile>) = launch {
        showLoading(fileUploadView)
        withContext(Dispatchers.IO) {
            for (file in mediaFiles) file.upload()
        }
        showSuccess(fileUploadView)
    }
}
