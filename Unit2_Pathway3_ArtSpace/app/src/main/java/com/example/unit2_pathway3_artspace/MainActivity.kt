package com.example.unit2_pathway3_artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit2_pathway3_artspace.ui.theme.Unit2_Pathway3_ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit2_Pathway3_ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceDisplaying(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun ArtworkDisplaying(
    index: Int,
    modifier: Modifier = Modifier
) {
    val imageResource = when (index) {
       0 -> R.drawable.uet
       1 -> R.drawable.uet_fit_30_years
       else -> R.drawable.vnu_lic
    }
    val titleResource = when (index) {
        0 -> R.string.uet_title
        1 -> R.string.uet_fit_title
        else -> R.string.vnu_lic_title
    }
    val artistResource = when (index) {
        0 -> R.string.uet_artist
        1 -> R.string.uet_fit_artist
        else -> R.string.vnu_lic_artist
    }
    val yearResource = when (index) {
        0 -> R.string.uet_year
        1 -> R.string.uet_fit_year
        else -> R.string.vnu_lic_year
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = 30.dp,
                    end = 30.dp
                )
                .height(500.dp),
            color = Color.White
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = modifier.padding(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(titleResource),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
//            modifier = modifier.padding(
//                start = 30.dp,
//                end = 30.dp
//            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(
                text = stringResource(artistResource),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "(" + stringResource(yearResource) + ")",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
            )
        }
    }
}
@Composable
fun ButtonDisplaying(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(20.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceDisplaying(modifier: Modifier = Modifier) {
    var index by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArtworkDisplaying(
            index = index,
            modifier = modifier
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            ButtonDisplaying(
                text = "Previous",
                onClick = { index = (index + 2) % 3  },
                modifier = modifier
                    .weight(1.0F)
            )
            Spacer(modifier = modifier.weight(0.5F))
            ButtonDisplaying(
                text = "Next",
                onClick = { index = (index + 1) % 3 },
                modifier = modifier.weight(1.0F)
            )
        }
    }
}
