package com.techhabiles.jokes

import android.content.Context
import android.content.res.AssetManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techhabiles.jokes.arch.JokeService
import com.techhabiles.jokes.models.Joke
import com.techhabiles.jokes.repos.JokesRepository
import com.techhabiles.jokes.ui.jokes.JokesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.InputStream
import java.lang.reflect.Type
import java.nio.charset.Charset

/**
 * Example local unit test, which will execute on the development machine.
 *
 * @author krish@techhabiles.com
 */

@RunWith(MockitoJUnitRunner::class)
class JokesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private var viewModel = JokesViewModel()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testViewModelInitialValues() {
        assertFalse(viewModel.loading.value!!)
        assertNull(viewModel.joke.value)
    }

    @Test
    fun testNetworkData() = runBlocking{
        val jokesService = Mockito.mock(JokeService::class.java)
        val repo = Mockito.mock(JokesRepository::class.java)
        Mockito.`when`(jokesService.getRandomJoke()).thenReturn(getJoke())
        repo.setJokeService(jokesService)
        val joke  = repo.getRandomJoke()
        assertEquals(joke.setup,"A test Setup")
        assertEquals(joke.id,1)
        assertEquals(joke.punchline,"A test punch line")
    }

    @Test
    fun testViewModel() = runTest {
        val viewModel = JokesViewModel()
        val jokesService = Mockito.mock(JokeService::class.java)
        val repo = Mockito.mock(JokesRepository::class.java)
        repo.setJokeService(jokesService)
        viewModel.setRepository(repo)
        val joke = getJoke()
        Mockito.`when`(repo.getRandomJoke()).thenReturn(joke)
        viewModel.fetchJoke()
        assertEquals(joke, viewModel.joke.getOrAwaitValue())

    }

    private fun getJoke(): Joke?{
        val type: Type = object : TypeToken<Joke?>() {}.getType()
        val json = getJokeJson()
        return Gson().fromJson(json, type)
    }
    fun getJokeJson(): String ?{

        try {
            val json = JokesViewModelTest::class.java.getResource("/mock_joke.json")!!.readText()
            return json
        } catch (e: Exception) {
            e.printStackTrace();
            return null;
        }

    }
}