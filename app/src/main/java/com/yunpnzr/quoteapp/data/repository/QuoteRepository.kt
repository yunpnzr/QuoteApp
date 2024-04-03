package com.yunpnzr.quoteapp.data.repository

import com.yunpnzr.quoteapp.data.datasource.QuoteDataSource
import com.yunpnzr.quoteapp.data.mapper.toQuotes
import com.yunpnzr.quoteapp.data.model.Quote
import com.yunpnzr.quoteapp.utils.ResultWrapper
import com.yunpnzr.quoteapp.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getRandomQuotes(): Flow<ResultWrapper<List<Quote>>>
}

class QuoteRepositoryImpl(private val dataSource: QuoteDataSource): QuoteRepository {
    override fun getRandomQuotes(): Flow<ResultWrapper<List<Quote>>> {
        return proceedFlow {
            dataSource.getRandomQuotes().toQuotes()
        }
    }
}