package com.architect.mynewsapp.ui.home
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.architect.mynewsapp.models.Article
import com.architect.mynewsapp.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _category = MutableLiveData<String>()
    private val _loading = MutableLiveData<Boolean>()

    // Expose the news as Flow<PagingData<Article>>
    val news: LiveData<PagingData<Article>> = _category.switchMap { category ->
        repository.getTopHeadlines(category)
            .cachedIn(viewModelScope)
            .onStart { _loading.value = true }
            .onEach { _loading.value = false }
            .asLiveData()
    }

    val loading: LiveData<Boolean> = _loading

    fun setCategory(category: String) {
        _category.value = category
    }

    fun bookmarkArticle(article: Article) {
        viewModelScope.launch {
            repository.bookmarkArticle(article)
        }
    }

    fun removeBookmark(url: String) {
        viewModelScope.launch {
            repository.removeBookmark(url)
        }
    }

    fun getBookmarkedArticles(): LiveData<List<Article>> {
        return repository.getAllBookmarkedArticles()
    }
}
