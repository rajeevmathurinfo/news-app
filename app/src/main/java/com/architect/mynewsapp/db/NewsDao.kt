package com.architect.mynewsapp.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.architect.mynewsapp.models.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getBookmarkedArticles(): LiveData<List<Article>>

    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun removeBookmark(url: String)

}

