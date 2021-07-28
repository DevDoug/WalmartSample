package com.walmartsample.ui.listing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.walmartsample.R
import dagger.hilt.android.AndroidEntryPoint


/**
 * Shows list of movie/show
 */
@AndroidEntryPoint
class ListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}