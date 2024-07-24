package com.architect.mynewsapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.architect.mynewsapp.databinding.ActivityDetailsBinding
import com.architect.mynewsapp.models.Article
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article: Article? = intent.getParcelableExtra("ARTICLE")
        loadArticleDetails(article)

    }

    private fun loadArticleDetails(article: Article?) {

        binding.title.text = article?.title
        binding.description.text = article?.description
        binding.publishedAt.text = article?.publishedAt

        Glide.with(this)
            .load(article?.urlToImage)
            .into(binding.image)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
