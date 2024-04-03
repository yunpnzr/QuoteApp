package com.yunpnzr.quoteapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yunpnzr.quoteapp.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: QuoteRepository): ViewModel() {
    //todo : tugas ambil data quotes
    fun getData() = repository.getRandomQuotes().asLiveData(Dispatchers.IO)
}