package com.techhabiles.jokes.ui

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 * Base Fragment for providing basic features like back press handling
 * @author krish@techhabiles.com
 */
open class ThFragment: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }

    open fun onBackPressed(){

    }

}