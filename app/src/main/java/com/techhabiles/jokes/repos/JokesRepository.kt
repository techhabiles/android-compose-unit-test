package com.techhabiles.jokes.repos

import com.techhabiles.jokes.arch.JokeService
import com.techhabiles.jokes.arch.RetroInstance
import com.techhabiles.jokes.models.Joke

/**
 *  Repository to fetch jokes form network using retrofit
 *  @author krish@techhabiles.com
 */
open class JokesRepository {

    // this is defined as var as want to use mockito to test Service
    private var jokeService = RetroInstance.jokesService

    suspend fun getRandomJoke() : Joke{
        return jokeService.getRandomJoke()
    }

    fun setJokeService(jokeService: JokeService){
        this.jokeService = jokeService
    }
}