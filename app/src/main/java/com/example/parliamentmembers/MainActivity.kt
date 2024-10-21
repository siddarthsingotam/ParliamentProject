/**
 * MainActivity.kt
 *
 * Date: 20-Oct-2024
 * Author: Siddarth Singotam
 *
 * This file contains the main activity for the Parliament Members application.
 * It sets up the main entry point for the app and defines the UI using Jetpack Compose.
 */

package com.example.parliamentmembers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.parliamentmembers.frontView.MinisterScreen
import com.example.parliamentmembers.ui.theme.ParliamentMembersTheme
import com.example.parliamentmembers.viewModel.MinisterViewModel
import com.example.parliamentmembers.viewModel.ViewModelProvider

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MinisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ViewModelProvider.provideMinisterViewModel(this)
        setContent {
            ParliamentMembersTheme {
                MinisterScreen(viewModel)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ParliamentPreview() {
    // NOTE: This snippet was only used for checking and building composable,
    // only run this project on the emulator to view ministers.
    val context = LocalContext.current
    val viewModel: MinisterViewModel = ViewModelProvider.provideMinisterViewModel(context)
    ParliamentMembersTheme {
        MinisterScreen(viewModel)
    }
}