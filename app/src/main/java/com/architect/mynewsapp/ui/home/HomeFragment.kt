package com.architect.mynewsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.architect.mynewsapp.databinding.FragmentHomeBinding
import com.architect.mynewsapp.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var newsAdapter: NewsPagingAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeNews()
        observeLoading()
        homeViewModel.setCategory("general")

    }

    private fun setupRecyclerView() {
        newsAdapter = NewsPagingAdapter { article ->
            homeViewModel.bookmarkArticle(article)
            Toast.makeText(requireContext(), "Article bookmarked", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            intent.putExtra("ARTICLE", article)
            startActivity(intent)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun observeNews() {
        homeViewModel.news.observe(viewLifecycleOwner) { pagingData ->
            newsAdapter.submitData(lifecycle, pagingData)
        }
    }

    private fun observeLoading() {
        homeViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



