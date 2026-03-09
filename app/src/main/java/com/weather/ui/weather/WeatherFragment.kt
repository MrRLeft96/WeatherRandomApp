package com.weather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.weather.ui.weather.screenstate.WeatherUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()

                WeatherScreen(
                    state = state,
                    onRefresh = { viewModel.loadWeather() }
                )
            }
        }
    }

    @Composable
    fun WeatherScreen(
        state: WeatherUiState,
        onRefresh: () -> Unit
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            when (state) {

                WeatherUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is WeatherUiState.Error -> {
                    Text(text = state.message)
                }

                is WeatherUiState.Success -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val composition by rememberLottieComposition(
                            LottieCompositionSpec.RawRes(
                                state.weather.iconRes
                            )
                        )
                        val progress by animateLottieCompositionAsState(
                            composition,
                            iterations = LottieConstants.IterateForever,
                            isPlaying = true
                        )

                        LottieAnimation(
                            composition = composition,
                            progress = {
                                progress
                            },
                            modifier = Modifier.size(160.dp)
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = state.weather.temperature,
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = state.weather.description,
                            modifier = Modifier
                                .padding(horizontal = 24.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium

                        )

                        if (state.weather.city.isNotEmpty()) {

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = state.weather.city,
                                style = MaterialTheme.typography.headlineMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(state.weather.coordinates)

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(onClick = onRefresh) {
                            Text("Refresh")
                        }
                    }
                }
            }
        }
    }
}