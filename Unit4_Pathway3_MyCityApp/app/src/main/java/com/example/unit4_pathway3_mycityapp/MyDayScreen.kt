package com.example.unit4_pathway3_mycityapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unit4_pathway3_mycityapp.model.DataSource.category
import com.example.unit4_pathway3_mycityapp.ui.ItemDetailsScreen
import com.example.unit4_pathway3_mycityapp.ui.MyCityViewModel
import com.example.unit4_pathway3_mycityapp.ui.SelectItemScreen

enum class MyCityScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Place(title = R.string.place),
    Recommended(title = R.string.recommended_place)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    currentScreen: MyCityScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MyDayApp(
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Start.name
    )
    // Create ViewModel
    val viewModel: MyCityViewModel = viewModel()

    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.Start.name) {
                SelectItemScreen(
                    items = category,
                    onSelectionChanged = {
                        viewModel.updateItem(it)
                        navController.navigate(MyCityScreen.Place.name)
                     },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = MyCityScreen.Place.name) {
                SelectItemScreen(
                    items = viewModel.chooseBaseOnCategory(),
                    onSelectionChanged = {
                        viewModel.updateItem(it)
                        navController.navigate(MyCityScreen.Recommended.name)
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = MyCityScreen.Recommended.name) {
                ItemDetailsScreen(
                    uiState = uiState,
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}