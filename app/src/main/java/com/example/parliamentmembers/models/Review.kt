/**
 * Review.kt
 *
 * Date: 20-Oct-2024
 * Author: Siddarth Singotam
 *
 * This file defines the Review entity for the Room database.
 * It represents a review made on a minister, including the minister's ID, rating, and comment text.
 */

package com.example.parliamentmembers.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Review(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ministerId: Int,
    val rating: Int,
    val comment: String
)