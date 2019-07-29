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
