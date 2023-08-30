package com.techhabiles.jokes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import com.techhabiles.jokes.ui.utils.NavigationUtils

/**
 *  Single Activity for hosting fragments
 *  @author krish@techhabiles.com
 *
 */

@ExperimentalMaterial3Api
class JokesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        NavigationUtils.navigateToSplash(this)

    }


}



