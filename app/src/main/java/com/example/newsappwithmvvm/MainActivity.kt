package com.example.newsappwithmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappwithmvvm.adapters.NewsAdapter
import com.example.newsappwithmvvm.dtos.BitCoinDto
import com.example.newsappwithmvvm.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var pageNo = 1
    private var loading = false
    private var bitCoinNewsList: ArrayList<BitCoinDto.Article> = ArrayList()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.init()
        loading = true
        newsViewModel.bitCoinNewsRepository.observe(this, Observer { newsResponse ->
            val newsArticles = newsResponse.articles
            bitCoinNewsList.addAll(newsArticles)
            newsAdapter.notifyDataSetChanged()
            loading = false
            println("Loaded page : $pageNo")
        })

        //nextPage
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(this@MainActivity, bitCoinNewsList)
        val layoutManager = LinearLayoutManager(this)
        rvHeadline.layoutManager = layoutManager
        rvHeadline.adapter = newsAdapter
        rvHeadline.itemAnimator = DefaultItemAnimator()
        rvHeadline.isNestedScrollingEnabled = true


        rvHeadline.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager, 3) {
            override fun onLoadMore() {
                if (loading) {
                    return
                }
                loading = true
                pageNo += 1
                newsViewModel.nextPage(pageNo)

                println("Loading page number from scroll listener: $pageNo")
            }
        })
    }
}