/**
 * MinisterViewModel.kt
 *
 * Date: 20-Oct-2024
 * Author: Siddarth Singotam
 *
 * This file contains the ViewModel for the MinisterScreen.
 * It manages the UI-related data for the MinisterScreen, including fetching and storing comments.
 */

package com.example.parliamentmembers.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembers.network.ApiService
import com.example.parliamentmembers.models.Review
import com.example.parliamentmembers.repository.RatingRepository
import com.example.parliamentmembers.models.Minister
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response


class MinisterViewModel(private val repository: RatingRepository) : ViewModel() {

    private val _ministers = MutableStateFlow<List<Minister>>(emptyList())
    val ministers = _ministers.asStateFlow()

    private val _comments = MutableStateFlow<List<Review>>(emptyList())
    val comments = _comments.asStateFlow()

    private val tag = "MinisterViewModel"

    // Fetches a list of ministers from the API and updates the state.
    fun fetchMinisters(apiService: ApiService) {
        Log.d(tag, "Fetching ministers from API...")
        apiService.getMinisters().enqueue(object : Callback<List<Minister>> {
            override fun onResponse(call: Call<List<Minister>>, response: Response<List<Minister>>) {
                if (response.isSuccessful) {
                    response.body()?.let { fetchedMinisters ->
                        val filteredMinisters = fetchedMinisters.filter { minister -> minister.minister }
                        _ministers.value = filteredMinisters
                        Log.d(tag, "Fetched ${filteredMinisters.size} ministers.")
                    } ?: run {
                        Log.d(tag, "Response body is null.")
                    }
                } else {
                    Log.e(tag, "Error fetching ministers: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Minister>>, t: Throwable) {
                Log.e(tag, "Failed to fetch ministers: ${t.message}", t)
            }
        })
    }

    // Inserts a new comment and fetches updated comments for the given minister.
    fun addComment(ministerId: Int, rating: Int, comment: String) {
        viewModelScope.launch {
            repository.insertComment(Review(ministerId = ministerId, rating = rating, comment = comment))
            fetchComments(ministerId)
        }
    }

    // Fetches comments for a specific minister and updates the state.
    fun fetchComments(ministerId: Int) {
        viewModelScope.launch {
            val fetchedComments = repository.getComments(ministerId)
            _comments.value = fetchedComments
        }
    }
}