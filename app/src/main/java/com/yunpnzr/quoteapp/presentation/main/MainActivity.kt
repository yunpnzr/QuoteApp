package com.yunpnzr.quoteapp.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.yunpnzr.quoteapp.data.datasource.QuoteApiDataSource
import com.yunpnzr.quoteapp.data.datasource.QuoteDataSource
import com.yunpnzr.quoteapp.data.repository.QuoteRepository
import com.yunpnzr.quoteapp.data.repository.QuoteRepositoryImpl
import com.yunpnzr.quoteapp.data.source.network.services.QuoteApiService
import com.yunpnzr.quoteapp.databinding.ActivityMainBinding
import com.yunpnzr.quoteapp.presentation.adapter.QuotesAdapter
import com.yunpnzr.quoteapp.utils.GenericViewModelFactory
import com.yunpnzr.quoteapp.utils.proceedWhen

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val quoteAdapter : QuotesAdapter by lazy {
        QuotesAdapter()
    }

    private val viewModel: MainViewModel by viewModels {
        val service = QuoteApiService.invoke()
        val dataSource: QuoteDataSource = QuoteApiDataSource(service)
        val repository : QuoteRepository = QuoteRepositoryImpl(dataSource)
        GenericViewModelFactory.create(MainViewModel(repository))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getData()
        expandCard()
    }

    private fun expandCard() {

    }

    private fun getData() {
        viewModel.getData().observe(this) { getQuote->
            getQuote.proceedWhen (
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.rvQuote.isVisible = false
                    binding.tvError.isVisible = false
                },
                doOnSuccess = { success ->
                    binding.pbLoading.isVisible = false
                    binding.rvQuote.isVisible = true
                    binding.tvError.isVisible = false
                    binding.rvQuote.apply {
                        adapter = this@MainActivity.quoteAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                    success.payload?.let {
                        quoteAdapter.submitData(it)
                    }
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.rvQuote.isVisible = false
                    binding.tvError.isVisible = true
                }
            )
        }

    }

}