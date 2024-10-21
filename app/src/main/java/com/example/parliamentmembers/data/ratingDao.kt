package com.example.parliamentmembers.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parliamentmembers.models.Review

@Dao
interface ratingDao {
    @Insert
    suspend fun insert(comment: Review)

    @Query("SELECT * FROM comments WHERE ministerId = :ministerId")
    suspend fun getComments(ministerId: Int): List<Review>
}