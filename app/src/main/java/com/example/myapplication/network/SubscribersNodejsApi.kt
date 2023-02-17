package com.example.myapplication.network

import com.example.myapplication.roomDatabase.Subscribers
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://subscribers.onrender.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface SubscribersNodejsApi{
    @GET("/subscribers")
    fun getSubscribersAsync(): Deferred<List<Subscribers>>

}

object NodejsApi{
    val subscribersApi : SubscribersNodejsApi by lazy {
        retrofit.create(SubscribersNodejsApi::class.java)
    }
}