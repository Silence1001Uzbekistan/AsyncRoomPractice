package com.example.asyncroompractice.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.asyncroompractice.Entity.User

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Query("select * from users")
    fun getAllUsers():List<User>

}