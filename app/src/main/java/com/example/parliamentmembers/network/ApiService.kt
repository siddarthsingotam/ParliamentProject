/**
 * ApiService.kt
 *
 * Date: 20-Oct-2024
 * Author: Siddarth Singotam
 *
 * This file defines the API service interface for fetching minister data from a remote server.
 * It includes methods to retrieve the list of ministers.
 */

package com.example.parliamentmembers.network

import com.example.parliamentmembers.models.Minister
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("~peterh/seating.json")
    fun getMinisters(): Call<List<Minister>>
}