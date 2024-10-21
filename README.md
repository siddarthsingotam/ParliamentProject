# Parliament Members App

This project is an Android application that displays information about parliament members. Users can view details about each minister, rate them, and leave comments. The app is built using Kotlin and Jetpack Compose.

## Features

- Display a list of ministers with their details.
- Navigate between ministers.
- Rate ministers on a scale of 0 to 5.
- Leave comments for ministers.
- View comments left by other users.

## Technologies Used

- **Kotlin**: The primary programming language used for the app.
- **Jetpack Compose**: For building the UI.
- **ViewModel**: To manage UI-related data in a lifecycle-conscious way.
- **LiveData**: To handle data that needs to be observed.
- **Coroutines**: For asynchronous programming.
- **Retrofit**: For network requests.
- **Coil**: For loading images.

## Screenshots

Image Sample 1             |  Image Sample 2
:-------------------------:|:-------------------------:
![](./app/src/main/java/com/example/parliamentmembers/pictures/Screenshot_20241022_000741.png){ width=45% }  |  ![](./app/src/main/java/com/example/parliamentmembers/pictures/Screenshot_20241022_000934.png){ width=45% }

## Getting Started

### Prerequisites

- Android Studio (version 2024.1.2 or later)
- Kotlin 1.5 or later

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/parliament-members-app.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.

### Running the App

1. Connect an Android device or start an emulator.
2. Click on the "Run" button in Android Studio.

## Project Structure

- `app/src/main/java/com/example/parliamentmembers/`
    - `frontView/`: Contains the UI components.
        - `FrontView.kt`: Main composable function for displaying the minister details and handling user interactions.
    - `viewModel/`: Contains the ViewModel classes.
        - `MinisterViewModel.kt`: Manages the data for the `MinisterScreen` composable.
    - `model/`: Contains the data models.
        - `Minister.kt`: Data class representing a minister.
        - `Comment.kt`: Data class representing a comment.
    - `repository/`: Contains the repository classes.
        - `MinisterRepository.kt`: Handles data operations for ministers.
    - `network/`: Contains the network-related classes.
        - `ApiService.kt`: Defines the API endpoints.
        - `RetrofitInstance.kt`: Provides the Retrofit instance.

## Usage

### Viewing Ministers

- The app displays a list of ministers. Users can navigate between ministers using the "Back" and "Next" buttons.

### Rating and Commenting

- Users can rate a minister by entering a value between 0 and 5 in the rating text field.
- Users can leave a comment after providing a valid rating.
- The "Add Comment" button is enabled only when a valid rating is provided.

### Viewing Comments

- Users can view comments left by other users for each minister.

## Code Overview

### `MinisterScreen` Composable

This composable function displays the details of a selected minister, allows users to rate and comment, and shows existing comments.

### ViewModel

The `MinisterViewModel` class manages the data for the `MinisterScreen` composable. It fetches the list of ministers and their comments from the repository.

### Repository

The `RatingRepository` class handles data operations for ministers, including fetching data from the network and storing it locally.

### Network

The `ApiService` interface defines the API endpoints.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Acknowledgements

- Jetpack Compose documentation
- Retrofit documentation
- Coil documentation
