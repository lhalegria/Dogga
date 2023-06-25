package dev.lhalegria.dogga.datasource.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dog.ceo/api/"

private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val gson: Gson = GsonBuilder()
    .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
    .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create()

val retrofitApi: Retrofit
    get() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
