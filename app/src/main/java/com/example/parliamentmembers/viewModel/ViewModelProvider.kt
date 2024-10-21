

package com.example.parliamentmembers.viewModel

import android.content.Context
import com.example.parliamentmembers.data.AppDatabase
import com.example.parliamentmembers.network.ApiService
import com.example.parliamentmembers.repository.RatingRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ViewModelProvider {

    fun provideMinisterViewModel(context: Context): MinisterViewModel {
        val db = AppDatabase.getDatabase(context)
        val commentDao = db.ratingDao()
        val repository = RatingRepository(commentDao)

        val apiService = Retrofit.Builder()
            .baseUrl("https://users.metropolia.fi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val viewModel = MinisterViewModel(repository)

        viewModel.fetchMinisters(apiService)

        return viewModel
    }
}