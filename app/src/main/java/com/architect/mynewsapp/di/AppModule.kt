package com.architect.mynewsapp.di
import android.content.Context
import com.architect.mynewsapp.db.NewsDao
import com.architect.mynewsapp.db.NewsDatabase
import com.architect.mynewsapp.network.NewsService
import com.architect.mynewsapp.repo.NewsRepository
import com.architect.mynewsapp.utill.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsService(): NewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsService: NewsService,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsRepository(newsService, newsDao)
    }
}


