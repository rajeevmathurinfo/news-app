package com.architect.mynewsapp.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.architect.mynewsapp.R
import com.architect.mynewsapp.databinding.ItemNewsBinding
import com.architect.mynewsapp.models.Article
import com.bumptech.glide.Glide

class NewsPagingAdapter(private val onItemClick: (Article) -> Unit) :
    PagingDataAdapter<Article, NewsPagingAdapter.NewsViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.title.text = article.title
            binding.description.text = article.description
            article?.urlToImage?.let { Log.d("image_url", it) }
            Glide.with(binding.image.context)
                .load(article.urlToImage)
                .into(binding.image)
            binding.root.setOnClickListener { onItemClick(article) }
        }
    }
}

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
