package com.techhabiles.jokes.ui.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techhabiles.jokes.models.Joke
import com.techhabiles.jokes.repos.JokesRepository
import kotlinx.coroutines.launch

/**
 *  Jokes ViewModel a glue between UI and Model(server data)
 *  @author krish@techhabiles.com
 */

class JokesViewModel: ViewModel() {

    // Set to var to use mockito for testing the view model in isolation
    private var repository = JokesRepository()

    private val _joke = MutableLiveData<Joke>()
    val joke : LiveData<Joke> = _joke

    private val _loading = MutableLiveData<Boolean>(false)
    val loading : LiveData<Boolean> = _loading

    fun fetchJoke(){
        viewModelScope.launch {
            try{
                _loading.value = true
                val randomJoke = repository.getRandomJoke()
                _loading.value = false
                _joke.value = randomJoke
            }catch(e:Exception){

            }
        }
    }

    fun setRepository(repo:JokesRepository){
        this.repository = repo
    }

}