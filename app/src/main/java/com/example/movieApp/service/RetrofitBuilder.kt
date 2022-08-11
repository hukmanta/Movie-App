package com.example.movieApp.service

import com.example.movieApp.BuildConfig
import com.example.movieApp.model.error.ErrorResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL = BuildConfig.SERVER_URL

    private val clientBuilder = OkHttpClient.Builder().also {
        it.connectTimeout(15, TimeUnit.SECONDS)
        it.writeTimeout(15, TimeUnit.SECONDS)
        it.readTimeout(15, TimeUnit.SECONDS)
    }

    private val retrofitBuilder :Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val movieAPI : MovieAPI by lazy {
        retrofitBuilder
            .build()
            .create(MovieAPI::class.java)
    }

    val errorConverter: Converter<ResponseBody, ErrorResponse> by lazy {
        retrofitBuilder.build().responseBodyConverter<ErrorResponse>(
            ErrorResponse::class.java, arrayOfNulls<Annotation>(0)
        )
    }

}