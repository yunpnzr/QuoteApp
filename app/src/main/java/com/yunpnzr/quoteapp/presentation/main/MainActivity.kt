package com.yunpnzr.quoteapp.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yunpnzr.quoteapp.data.datasource.QuoteApiDataSource
import com.yunpnzr.quoteapp.data.datasource.QuoteDataSource
import com.yunpnzr.quoteapp.data.repository.QuoteRepository
import com.yunpnzr.quoteapp.data.repository.QuoteRepositoryImpl
import com.yunpnzr.quoteapp.data.source.network.services.QuoteApiService
import com.yunpnzr.quoteapp.databinding.ActivityMainBinding
import com.yunpnzr.quoteapp.utils.GenericViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
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
    }

}