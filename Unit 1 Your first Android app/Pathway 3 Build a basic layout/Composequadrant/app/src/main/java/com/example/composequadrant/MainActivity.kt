package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.BottomLeftQuadrant
import com.example.composequadrant.ui.theme.BottomRightQuadrant
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme
import com.example.composequadrant.ui.theme.TopLeftQuadrant
import com.example.composequadrant.ui.theme.TopRightQuadrant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComposeCardsDisplaying(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CardDetails(title: String,
                content: String,
                color: Color,
                modifier: Modifier = Modifier) {
    Surface(
        color = color,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 16.dp),
                fontWeight = Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = content,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun ComposeCardsDisplaying(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.weight(1f, true)
        ) {
            CardDetails(
                title = stringResource(R.string.top_left_quadrant_title),
                content = stringResource(R.string.top_left_quadrant_content),
                color = TopLeftQuadrant,
                modifier = Modifier.weight(1f, true)
            )
            CardDetails(
                title = stringResource(R.string.top_right_quadrant_title),
                content = stringResource(R.string.top_right_quadrant_content),
                color = TopRightQuadrant,
                modifier = Modifier.weight(1f, true)
            )
        }
        Row(
            modifier = Modifier.weight(1f, true)
        ) {
            CardDetails(
                title = stringResource(R.string.bottom_left_quadrant_title),
                content = stringResource(R.string.bottom_left_quadrant_content),
                color = BottomLeftQuadrant,
                modifier = Modifier.weight(1f, true)
            )
            CardDetails(
                title = stringResource(R.string.bottom_right_quadrant_title),
                content = stringResource(R.string.bottom_right_quadrant_content),
                color = BottomRightQuadrant,
                modifier = Modifier.weight(1f, true)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            CardDetails(
//                title = stringResource(R.string.top_left_quadrant_title),
//                content = stringResource(R.string.top_left_quadrant_content),
//                color = TopLeftQuadrant,
//                modifier = Modifier.padding(innerPadding)
//            )
            ComposeCardsDisplaying(modifier = Modifier.padding(innerPadding))
        }
    }
}