package com.helloworld.basictable

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface TaskDAO {

    @Upsert
    suspend fun upsertData(tasks: Tasks)  //tasks jo entity banaye thi usko object ki form me pass krna pdega

    @Delete
    suspend fun deleteData(tasks: Tasks)

    @Query("SELECT * FROM Tasks")
    fun getData() : LiveData<List<Tasks>> //getData m kuch pass nahi krna h, but List ke form m aayega


}