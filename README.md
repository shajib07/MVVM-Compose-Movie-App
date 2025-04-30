# MovieMate 🎬

MovieMate is a modern Android application built with Jetpack Compose that allows users to explore and discover movies and TV series. The app provides a beautiful and intuitive interface for browsing trending content, searching for specific titles, and viewing detailed information about movies and TV shows.

## Features ✨

- **Movies Section**
  - Now Playing Movies
  - Popular Movies
  - Top Rated Movies
  - Upcoming Movies
  - Movie Details

- **TV Series Section**
  - Airing Today
  - On The Air
  - Popular TV Series
  - Top Rated TV Series
  - TV Series Details

- **Modern UI**
  - Built with Jetpack Compose
  - Material Design 3
  - Dark/Light theme support
  - Smooth animations and transitions

- **Technical Features**
  - MVVM Architecture
  - Dependency Injection with Hilt
  - Paging 3 for efficient data loading
  - Room Database for local caching
  - Retrofit for API calls
  - Navigation Component

## Tech Stack 🛠️

- **Language**: Kotlin
- **Architecture**: MVVM
- **UI**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Networking**: Retrofit
- **Database**: Room
- **Paging**: Paging 3
- **Navigation**: Navigation Component
- **Image Loading**: Coil
- **Testing**: JUnit, Espresso

## Getting Started 🚀

### Prerequisites

- Android Studio Hedgehog | 2023.1.1 or later
- JDK 17 or later
- Android SDK 34 or later

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/shajib07/MovieMate.git
   ```

2. Open the project in Android Studio

3. Create a `local.properties` file in the root directory and add your TMDB API key:
   ```
   API_KEY=your_tmdb_api_key_here
   ```

4. Sync the project with Gradle files

5. Run the app on an emulator or physical device

## Project Structure 📁

```
app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/atahar/moviemate/
│   │   │       ├── data/           # Data layer
│   │   │       │   ├── datasource/ # Remote and local data sources
│   │   │       │   ├── model/      # Data models
│   │   │       │   └── repository/ # Repository implementations
│   │   │       ├── di/            # Dependency injection
│   │   │       ├── navigation/    # Navigation components
│   │   │       ├── ui/            # UI components
│   │   │       │   ├── screens/   # Screen composables
│   │   │       │   └── theme/     # Theme definitions
│   │   │       └── utils/         # Utility classes
```

## API Integration 🔗

MovieMate uses The Movie Database (TMDB) API to fetch movie and TV series data. You'll need to:
1. Create an account on [TMDB](https://www.themoviedb.org/)
2. Get your API key
3. Add it to the `local.properties` file

## Contributing 🤝

Contributions are welcome! Please feel free to submit a Pull Request.

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments 🙏

- [The Movie Database (TMDB)](https://www.themoviedb.org/) for providing the API
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for the modern UI toolkit
- [Android Developers](https://developer.android.com/) for the amazing documentation

## Contact 📧

For any questions or suggestions, please feel free to reach out!

---

Made with ❤️ using Jetpack Compose 
