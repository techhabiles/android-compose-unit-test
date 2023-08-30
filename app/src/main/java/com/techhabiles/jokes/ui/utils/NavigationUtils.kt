package com.techhabiles.jokes.ui.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.techhabiles.jokes.R
import com.techhabiles.jokes.ui.jokes.JokesFragment
import com.techhabiles.jokes.ui.splash.SplashFragment
/**
 *  Utility class to help for navigation between screens or fragments
 *  @author krish@techhabiles.com
 */

@ExperimentalMaterial3Api
class NavigationUtils {

    companion object {
        fun navigateToJokes(activity: FragmentActivity) {
            val fragment = JokesFragment()
            show(activity.supportFragmentManager, fragment, JokesFragment.TAG, true)
        }

        fun navigateToSplash(activity: FragmentActivity) {
            val fragment = SplashFragment()
            show(activity.supportFragmentManager, fragment, SplashFragment.TAG, false)
        }

        private fun show(fragmentManager:FragmentManager, fragment :Fragment, tag:String, backStack: Boolean){
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.root, fragment,tag)
            if(backStack){
                transaction.addToBackStack(tag)
            }else{
                transaction.disallowAddToBackStack()
            }
            transaction.commit()

        }
    }


}