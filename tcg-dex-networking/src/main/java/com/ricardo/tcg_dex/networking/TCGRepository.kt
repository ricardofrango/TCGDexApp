package com.ricardo.tcg_dex.networking

import com.ricardo.tcg_dex.networking.api.TCGDexAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TCGRepository(baseURl: String) : ITCGRepository {

    private val repository = Retrofit.Builder()
        .baseUrl(baseURl)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TCGDexAPI::class.java)

    override suspend fun getCards() = repository.getCards()
}
