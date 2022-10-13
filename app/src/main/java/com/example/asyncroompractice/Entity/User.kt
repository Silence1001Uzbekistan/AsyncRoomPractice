package com.example.asyncroompractice.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
class User {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var username: String? = null
    var password: String? = null

}