**News App**
Overview
This is a news application built using modern Android development practices, including Hilt for dependency injection, ViewBinding for view management, Retrofit for network operations, and Paging for efficient data loading. The app allows users to view top headlines, bookmark articles, and see detailed views of individual articles.

**Features**
Display Top Headlines: Fetch and display top headlines from a news API with support for pagination.
Bookmark Articles: Save articles to a local Room database for offline access.
View Article Details: Display detailed information about a selected article.
Filter Articles: Ensure only articles with images are displayed.
Loading Indicators: Show loading indicators during data fetch and pagination.
Architecture
MVVM: Separates UI (View) from business logic (ViewModel) and data handling (Repository).
Paging: Efficiently loads and displays large datasets in chunks.
Hilt: Simplifies dependency injection.
Room: Provides a local database for storing bookmarked articles.
Retrofit: Handles network requests to fetch news data.
**Prerequisites**
Android Studio Arctic Fox or higher
Kotlin 1.5 or higher
Gradle 7.0 or higher
Dependencies
Hilt: Dependency Injection
Retrofit: Networking
Room: Local Database
Paging3: Paging
ViewBinding: Simplified view access
