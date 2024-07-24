package com.architect.mynewsapp.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.architect.mynewsapp.db.NewsDao
import com.architect.mynewsapp.models.Article
import com.architect.mynewsapp.network.NewsService
import com.architect.mynewsapp.ui.home.NewsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao
) {

    // Function to fetch news with pagination from the API
    fun getTopHeadlines(category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // Number of items to load per page
                initialLoadSize = 10, // Initial load size to request
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsService, category) }
        ).flow
    }

    // Function to bookmark an article
    suspend fun bookmarkArticle(article: Article) {
        newsDao.bookmarkArticle(article)
    }

    // Function to remove a bookmark
    suspend fun removeBookmark(url: String) {
        newsDao.removeBookmark(url)
    }

    fun getAllBookmarkedArticles(): LiveData<List<Article>> {
        return newsDao.getBookmarkedArticles()
    }
}

