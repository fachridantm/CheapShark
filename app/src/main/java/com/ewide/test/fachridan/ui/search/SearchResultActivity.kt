package com.ewide.test.fachridan.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ewide.test.fachridan.databinding.ActivitySearchResultBinding

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Handle search result
    }
}