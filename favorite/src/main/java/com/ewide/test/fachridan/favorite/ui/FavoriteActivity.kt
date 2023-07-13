package com.ewide.test.fachridan.favorite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ewide.test.fachridan.favorite.R
import com.ewide.test.fachridan.favorite.di.ViewModelFactory
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }
}