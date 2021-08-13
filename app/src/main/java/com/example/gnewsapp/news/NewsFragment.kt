package com.example.gnewsapp.news

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gnewsapp.R
import com.example.gnewsapp.databinding.FragmentNewsBinding
import com.example.gnewsapp.news.articles.ArticleActivity
import com.example.gnewsapp.viewbindings.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {
    private val viewModel: NewsViewModel by viewModels()
    private val binding by viewBinding(FragmentNewsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NewsAdapter(
            { articleItem -> viewModel.onItemSelected(articleItem) }, Glide.with(this)
        )
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )

        viewModel.itemsLiveData().observe(viewLifecycleOwner) { items ->
            adapter.setItems(items)
        }

        viewModel.progressLiveData().observe(viewLifecycleOwner) { showProgress ->
            binding.swipeRefresh.isRefreshing = showProgress
        }

        viewModel.selectedItemLiveData().observe(viewLifecycleOwner) {
            val intent = ArticleActivity.createIntent(requireContext(), it)
            startActivity(intent)
        }

        viewModel.errorLiveData().observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .show()
        }

        viewModel.onCreated()

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }
}
