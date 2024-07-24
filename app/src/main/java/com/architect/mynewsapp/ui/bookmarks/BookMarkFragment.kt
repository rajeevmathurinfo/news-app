package com.architect.mynewsapp.ui.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.architect.mynewsapp.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookMarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private lateinit var newsAdapter: BookmarkedArticlesAdapter
    private val bookMarkViewModel: BookMarkViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeBookmarkedArticles()
        observeLoading()
    }

    private fun setupRecyclerView() {
        newsAdapter = BookmarkedArticlesAdapter { article ->
            // Handle item click
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun observeBookmarkedArticles() {
        bookMarkViewModel.bookmarkedArticles.observe(viewLifecycleOwner) { articles ->
            newsAdapter.submitList(articles)
        }
    }

    private fun observeLoading() {
        bookMarkViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


