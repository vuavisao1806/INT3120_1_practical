package com.example.unit3_pathway3_superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.unit3_pathway3_superheroesapp.model.HeroesRepository
import com.example.unit3_pathway3_superheroesapp.ui.theme.Unit3_Pathway3_SuperheroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3_Pathway3_SuperheroesAppTheme {
                Scaffold(
                    topBar = {
                        SuperHeroesTopBar()
                    },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    HeroList(
                        heroList = HeroesRepository.heroes,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesTopBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HeroAppPreview() {
    Unit3_Pathway3_SuperheroesAppTheme {
        Scaffold(
            topBar = {
                SuperHeroesTopBar()
            },
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            HeroList(
                heroList = HeroesRepository.heroes,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}