package com.torr.coroutinesplayground

import android.util.Log
import kotlin.random.Random
import kotlin.random.nextInt

object GeoTracker {
    fun getCurrentLocation(): Int {
        return Random.nextInt(IntRange(100000000, 599999999))
    }
}