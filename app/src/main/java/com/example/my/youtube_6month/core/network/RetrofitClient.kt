package com.example.my.youtube_6month.core.network

import com.example.my.youtube_6month.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    factory { provideApi(get()) }
}

fun provideOkhttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("\"https://www.googleapis.com/youtube/v3/\"")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

}

fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)