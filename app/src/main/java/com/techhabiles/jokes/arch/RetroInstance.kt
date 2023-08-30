package com.techhabiles.jokes.arch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Retrofit instance creating
 *  @author krish@techhabiles.com
 */
object RetroInstance {
    private const val BASE_URL = "https://official-joke-api.appspot.com"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val jokesService: JokeService by lazy{
        retrofit.create(JokeService::class.java)
    }

}