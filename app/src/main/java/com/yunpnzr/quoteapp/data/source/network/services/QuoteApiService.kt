package com.yunpnzr.quoteapp.data.source.network.services

import com.yunpnzr.quoteapp.BuildConfig
import com.yunpnzr.quoteapp.data.source.network.model.QuoteResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface QuoteApiService {
    @GET("quotes")
    suspend fun getRandomQuotes() : List<QuoteResponse>

    companion object{
        @JvmStatic
        operator fun invoke(): QuoteApiService{
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(QuoteApiService::class.java)
        }
    }
}