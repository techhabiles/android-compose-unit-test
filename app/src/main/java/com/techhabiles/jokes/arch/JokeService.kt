package com.techhabiles.jokes.arch

import com.techhabiles.jokes.models.Joke
import retrofit2.http.GET

/**
 *  Service interface to be used by Retrofit to provide implementation
 *  @author krish@techhabiles.com
 */
interface JokeService {
    @GET("/random_joke")
    suspend fun getRandomJoke(): Joke

}