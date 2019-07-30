package com.torr.coroutinesplayground.stuff

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FooDao {
    @Query("SELECT * FROM Foo")
    suspend fun getAllFoos(): List<Foo>
}