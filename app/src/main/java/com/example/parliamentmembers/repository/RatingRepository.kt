/**
 * RatingRepository.kt
 *
 * Date: 20-Oct-2024
 * Author: Siddarth Singotam
 *
 * This file defines the repository for accessing review data.
 * It provides methods to insert reviews and fetch comments for a specific minister from the Room database.
 */

package com.example.parliamentmembers.repository

import com.example.parliamentmembers.data.ratingDao
import com.example.parliamentmembers.models.Review

class RatingRepository(private val ratingDao: ratingDao) {
    suspend fun insertComment(comment: Review) {
        ratingDao.insert(comment)
    }

    suspend fun getComments(ministerId: Int): List<Review> {
        return ratingDao.getComments(ministerId)
    }
}