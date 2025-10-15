@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.unit6_pathway3_flightsearch

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.unit6_pathway3_flightsearch.ui.FlightSearchScreen

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun FlightSearchApp() {
    FlightSearchScreen()
}


@Composable
fun FlightSearchTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
    )
}
