package com.weather.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.weather.ui.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, WeatherFragment())
                .commit()
        }
    }
}