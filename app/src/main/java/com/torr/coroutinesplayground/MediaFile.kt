package com.torr.coroutinesplayground

import android.util.Log

class MediaFile {

    companion object {
        fun loadAndRender(fileName: String): MediaFile {
            Thread.sleep(1500)
            Log.d("MediaFile", "File rendered")
            return MediaFile()
        }
    }

    fun upload() {
        Thread.sleep(1000)
        Log.d("MediaFile", "File uploaded")
    }
}