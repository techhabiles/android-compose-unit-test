package com.techhabiles.jokes.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.jokes.ui.theme.TimeSheetTheme
import com.techhabiles.jokes.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import com.techhabiles.jokes.ui.ThFragment
import com.techhabiles.jokes.ui.thFontFamily

/**
 *  Jokes fragment usages Compose to build UI fetch data using ViewModel and display it
 *
 * @author krish@techhabiles.com
 *
 */


@ExperimentalMaterial3Api
class JokesFragment : ThFragment() {

    private val jokesViewModel: JokesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TimeSheetTheme {
                    JokeScreen(jokesViewModel)

                }
            }
        }
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }


    @Composable
    fun JokeScreen(viewModel: JokesViewModel) {

        val loading by viewModel.loading.observeAsState(initial = false)
        val joke by viewModel.joke.observeAsState()

        LaunchedEffect(Unit) {
            jokesViewModel.fetchJoke()
        }

        ConstraintLayout(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            val (title, card, load) = createRefs()
            Text(
                text = "Jokes", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontFamily = thFontFamily,
                fontWeight = FontWeight.Bold
            )



            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp, start = 10.dp, end = 10.dp, bottom = 50.dp)
                    .border(2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                    .constrainAs(card) {
                        top.linkTo(title.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(title.top)
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.techhabiles),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp), contentDescription = "Logo"
                )
                if (loading) {
                    Text(
                        text = "Loading...",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        fontSize = 25.sp,
                        fontFamily = thFontFamily,
                        fontWeight = FontWeight.Normal

                    )
                }
                joke?.let {

                    if (!loading) {
                        Text(
                            text = it.setup!!,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally),
                            fontSize = 25.sp,
                            fontFamily = thFontFamily,
                            fontWeight = FontWeight.Normal

                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = it.punchline!!,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            fontSize = 25.sp,
                            fontFamily = thFontFamily,
                            fontWeight = FontWeight.Normal

                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    joke?.let {
                        Button(
                            onClick = { jokesViewModel.fetchJoke() },
                            modifier = Modifier
                                .border(2.dp, Color.Black, shape = RectangleShape),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),

                            shape = RectangleShape,

                            ) {
                            Text(
                                text = "New Joke",
                                modifier = Modifier.background(color = Color.Transparent),
                                fontFamily = thFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 22.sp
                            )

                        }
                    }


                    Button(
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                        onClick = { requireActivity().finish() },
                        modifier = Modifier
                            .border(2.dp, Color.Black, shape = RectangleShape),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),

                        shape = RectangleShape,

                        ) {
                        Text(
                            text = "Close",
                            modifier = Modifier.background(color = Color.Transparent),
                            fontFamily = thFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp
                        )
                    }
                }


            }

            if (loading) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                    .constrainAs(load) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .height(24.dp)
                            .fillMaxWidth(),
                        trackColor = Color(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.purple_150
                            )
                        ),
                        color = Color(ContextCompat.getColor(requireContext(), R.color.purple_200))

                    )
                }


            }
        }
    }

    companion object {
        val TAG = "JokesFragment"
    }


}