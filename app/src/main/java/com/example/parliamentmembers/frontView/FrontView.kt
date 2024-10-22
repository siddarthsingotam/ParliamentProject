/**
 * FrontView.kt
 *
 * Date: 20-Oct-2024
 * Author: Siddarth Singotam
 *
 * This file contains the composable function MinisterScreen.
 * It displays the details of a selected minister, allows users to add comments and ratings,
 * and shows the list of comments for the selected minister.
 */

package com.example.parliamentmembers.frontView

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.parliamentmembers.viewModel.MinisterViewModel

@Composable
fun MinisterScreen(viewModel: MinisterViewModel) {
    var selectedMinisterIndex by remember { mutableIntStateOf(0) }
    var commentText by remember { mutableStateOf(TextFieldValue()) }
    var rating by remember { mutableStateOf("") }
    var ratingError by remember { mutableStateOf(false) }
    var ratingEmptyError by remember { mutableStateOf(false) }

    val ministers by viewModel.ministers.collectAsState()
    val comments by viewModel.comments.collectAsState()

    // Keyboard on-tap trackers
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    // Fetch comments for the selected minister when the index changes
    LaunchedEffect(selectedMinisterIndex) {
        if (ministers.isNotEmpty()) {
            val selectedMinister = ministers[selectedMinisterIndex]
            viewModel.fetchComments(selectedMinister.hetekaId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus() // Clear focus to hide the keyboard
                    hideKeyboard(context) // Hide the keyboard
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (ministers.isNotEmpty()) {
            val minister = ministers[selectedMinisterIndex]
            val imageUrl = "https://avoindata.eduskunta.fi/${minister.pictureUrl}"

            Log.d("MinisterScreen", "Loading image from URL: $imageUrl")

            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Minister Picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 64.dp, end = 64.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "${minister.firstname} ${minister.lastname}",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(text = "Party: ${minister.party}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = {
                    selectedMinisterIndex = if (selectedMinisterIndex > 0) {
                        selectedMinisterIndex - 1
                    } else {
                        ministers.size - 1
                    }
                }) {
                    Text(text = "Back")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    selectedMinisterIndex = (selectedMinisterIndex + 1) % ministers.size
                }) {
                    Text(text = "Next")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Rating TextField with validation
            TextField(
                value = rating,
                onValueChange = {
                    rating = it
                    val newRating = it.toIntOrNull()
                    ratingEmptyError = it.isEmpty()
                    ratingError = newRating == null || newRating !in 0..5

                },
                label = { Text("Rating (0-5)") },
                isError = ratingError || ratingEmptyError,
                placeholder = { Text("0-5") },
                textStyle = TextStyle(color = Color.Blue),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (ratingEmptyError) {
                Text(
                    text = "Rating cannot be empty",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            } else if (ratingError) {
                Text(
                    text = "Please enter a valid rating between 0 and 5",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Comment TextField
            TextField(
                value = commentText,
                onValueChange = { commentText = it },
                label = { Text("Comment") },
                maxLines = 4,
                enabled = !ratingError && !ratingEmptyError
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newRating = rating.toIntOrNull() ?: 0
                    if (!ratingError && !ratingEmptyError) {
                        viewModel.addComment(minister.hetekaId, newRating, commentText.text)
                        commentText = TextFieldValue("")
                        rating = ""
                        Log.d("MinisterScreen", "Adding comment: ${commentText.text}, rating: $newRating")
                    }
                },
                enabled = !ratingError && !ratingEmptyError
            ) {
                Text(text = "Add Comment")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Comments:", style = MaterialTheme.typography.headlineSmall)
            for (comment in comments) {
                Text(
                    text = "Rating: ${comment.rating}, Comment: ${comment.comment}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        } else {
            Text("No ministers available.", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Hide keyboard onTap
fun hideKeyboard(context: Context) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow((context as Activity).currentFocus?.windowToken, 0)
}