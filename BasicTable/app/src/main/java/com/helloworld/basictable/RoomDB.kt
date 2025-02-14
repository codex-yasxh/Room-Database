package com.helloworld.basictable

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase


@Database([
    Tasks::class  //jo table/entity ka name thaa wo aayega
],
   version = 1
)
abstract class RoomDB : RoomDatabase() {

    abstract val dao : TaskDAO


}