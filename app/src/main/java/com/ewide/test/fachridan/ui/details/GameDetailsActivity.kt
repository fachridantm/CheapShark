package com.ewide.test.fachridan.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.utils.Constants.EXTRA_DATA
import com.ewide.test.fachridan.core.utils.getParcelableData
import com.ewide.test.fachridan.databinding.ActivityGameDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
    }

    private fun initData() {
        val data = intent.getParcelableData<Deal>(EXTRA_DATA)

        with(binding) {

        }
    }

    private fun initView() {
        with(binding) {

        }
    }
}