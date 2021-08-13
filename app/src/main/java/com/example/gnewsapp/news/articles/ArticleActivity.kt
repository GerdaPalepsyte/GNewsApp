package com.example.gnewsapp.news.articles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gnewsapp.databinding.ActivityArticleBinding
import com.example.gnewsapp.news.NewsItem
import com.example.gnewsapp.viewbindings.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {
    private val article: NewsItem by lazy { intent.getParcelableExtra(NAME_ARTICLE)!! }
    private val binding by viewBinding(ActivityArticleBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.webView.loadUrl(article.url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val NAME_ARTICLE = "article"

        fun createIntent(context: Context, newsItem: NewsItem): Intent {
            return Intent(context, ArticleActivity::class.java).putExtra(
                NAME_ARTICLE,
                newsItem
            )
        }
    }
}
