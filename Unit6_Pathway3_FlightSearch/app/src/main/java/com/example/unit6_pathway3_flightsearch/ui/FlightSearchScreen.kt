package com.example.unit6_pathway3_flightsearch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unit6_pathway3_flightsearch.FlightSearchTopAppBar
import com.example.unit6_pathway3_flightsearch.R
import com.example.unit6_pathway3_flightsearch.data.Airport
import com.example.unit6_pathway3_flightsearch.data.FlightDescription
import com.example.unit6_pathway3_flightsearch.ui.theme.inversePrimaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val text by viewModel.search.collectAsState()
    val isTyping by viewModel.isTyping.collectAsState()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FlightSearchTopAppBar(
                title = stringResource(R.string.app_name),
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = viewModel::onTyping,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.Search, null) },
                placeholder = { Text(text = "Enter departure airport") },
                singleLine = true
            )
            Spacer(Modifier.height(8.dp))
            when {
                text.isEmpty() -> {
                    val favoriteFlights by viewModel.favoriteFlights.collectAsState()
                    FavoriteFlightList(
                        flightDescriptionList = favoriteFlights,
                        onFavoriteClick = viewModel::changeFavoriteFlight,
                    )
                }
                isTyping -> {
                    val suggestion by viewModel.getSuggestionAirports(text).collectAsState(emptyList())
                    FlightSuggestion(
                        airportList = suggestion,
                        onClick = viewModel::onChoosingAirport,
                    )
                }

                else -> {
                    val selectedDepAirport by viewModel.selectedDepAirport.collectAsState()
                    val flightList by viewModel.flightList.collectAsState(emptyList())
                    FlightList(
                        depAirport = selectedDepAirport!!,
                        flightDescriptionList = flightList,
                        onFavoriteClick = viewModel::changeFavoriteFlight,
                    )
                }
            }
        }
    }
}

@Composable
fun FlightSuggestion(
    airportList: List<Airport>,
    onClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(airportList) { airport ->
            TextButton(
                onClick = { onClick(airport) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "${airport.iataCode}  ${airport.name}")
            }
        }
    }
}


@Composable
fun FlightList(
    depAirport: Airport,
    flightDescriptionList: List<FlightDescription>,
    onFavoriteClick: (FlightDescription) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.flights_from, depAirport.iataCode),
        style = MaterialTheme.typography.titleMedium
    )
    LazyColumn {
        items(flightDescriptionList) { flight ->
            FlightDescriptionCard(
                flight = flight,
                onFavoriteClick = { onFavoriteClick(flight) }
            )
        }
    }
}

@Composable
fun FavoriteFlightList(
    flightDescriptionList: List<FlightDescription>,
    onFavoriteClick: (FlightDescription) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.favorite_routes),
        style = MaterialTheme.typography.titleLarge
    )
    LazyColumn {
        items(flightDescriptionList) { flight ->
            FlightDescriptionCard(
                flight = flight,
                onFavoriteClick = { onFavoriteClick(flight) }
            )
        }
    }
}

@Composable
fun FlightDescriptionCard(
    flight: FlightDescription,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = stringResource(R.string.depart),
                    style = MaterialTheme.typography.labelMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = flight.departure.iataCode,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = flight.departure.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.arrive),
                    style = MaterialTheme.typography.labelMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = flight.destination.iataCode,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = flight.destination.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(R.string.favorite),
                    tint = if (flight.isFavorite) inversePrimaryLight else Color.Gray
                )
            }
        }
    }
}