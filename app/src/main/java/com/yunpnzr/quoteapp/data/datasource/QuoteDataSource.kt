package com.yunpnzr.quoteapp.data.datasource

import com.yunpnzr.quoteapp.data.source.network.model.QuoteResponse
import com.yunpnzr.quoteapp.data.source.network.services.QuoteApiService

interface QuoteDataSource {
    suspend fun getRandomQuotes(): List<QuoteResponse>
}

class QuoteApiDataSource(private val services: QuoteApiService): QuoteDataSource{
    override suspend fun getRandomQuotes(): List<QuoteResponse> {
        return services.getRandomQuotes()
    }

}