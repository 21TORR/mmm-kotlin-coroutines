package com.torr.coroutinesplayground.stuff

import androidx.room.Dao
import androidx.room.Query
import retrofit2.Call
import retrofit2.http.GET

interface FooApi {
    @GET("/foo")
    fun loadAllFoos(): Call<List<Foo>>
}