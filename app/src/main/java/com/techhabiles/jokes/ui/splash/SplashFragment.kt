package com.techhabiles.jokes.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jokes.ui.theme.TimeSheetTheme
import com.techhabiles.jokes.R
import com.techhabiles.jokes.ui.ThFragment
import com.techhabiles.jokes.ui.utils.NavigationUtils
import kotlinx.coroutines.delay

/**
 *  Splash Screen Fragment
 *  @author krish@techhabiles.com
 */
@ExperimentalMaterial3Api
class SplashFragment : ThFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TimeSheetTheme {
                    SplashScreen()

                }
            }
        }
    }

    @Composable
    fun SplashScreen(){
        Image(
            painter = painterResource(id = R.drawable.techhabiles),
            modifier = Modifier
                .fillMaxSize(), contentDescription = "Logo"
        )

        LaunchedEffect(Unit){
            delay(3000)
            NavigationUtils.navigateToJokes(requireActivity())

        }
    }

    companion object{
        val TAG = "SplashFragment"
    }

}

