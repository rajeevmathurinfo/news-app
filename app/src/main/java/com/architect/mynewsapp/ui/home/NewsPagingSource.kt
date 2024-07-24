package com.architect.mynewsapp.ui.home
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.architect.mynewsapp.models.Article
import com.architect.mynewsapp.network.NewsService
import com.architect.mynewsapp.utill.Constants.Companion.API_KEY

import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val newsService: NewsService,
    private val category: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = newsService.getTopHeadlines(API_KEY, category, page)
            val articles = response.articles
            val nextPage = if (articles.isEmpty()) null else page + 1

            LoadResult.Page(
                data = articles,
                prevKey = null, // We only paginate forward
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
}
