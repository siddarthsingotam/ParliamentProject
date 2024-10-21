/**
 * Minister.kt
 *
 * Date: 20-Oct-2024
 * Author: Siddarth Singotam
 *
 * This file defines the Minister data class.
 * It represents a minister with properties such as ID, first name, last name, party, and picture URL.
 */

package com.example.parliamentmembers.models

data class Minister(
    val hetekaId: Int,
    val seatNumber: Int,
    val firstname: String,
    val lastname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String
)