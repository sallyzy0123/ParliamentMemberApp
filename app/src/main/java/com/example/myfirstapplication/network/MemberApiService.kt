package com.example.myfirstapplication.network

import com.example.myfirstapplication.data.ParliamentMember
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// date: 11 Oct 2022


// the base url address
private const val BASE_URL =
    "https://users.metropolia.fi/~peterh/"

// Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// The Retrofit object with the Moshi converter.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// A public interface that exposes the [getMembers] method
interface MemberApiService {
    @GET("seating.json")
    suspend fun getMembers(): List<ParliamentMember>
}

// A public Api object that exposes the lazy-initialized Retrofit service
object MemberApi {
    val retrofitService: MemberApiService by lazy {
        retrofit.create(MemberApiService::class.java) }
}

