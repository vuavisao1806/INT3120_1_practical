package com.example.unit3_pathway3_30daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit3_pathway3_30daysapp.model.DataSource
import com.example.unit3_pathway3_30daysapp.model.Flower
import com.example.unit3_pathway3_30daysapp.ui.theme.Shapes
import com.example.unit3_pathway3_30daysapp.ui.theme.Unit3_Pathway3_30DaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3_Pathway3_30DaysAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        FlowerTopAppBar()
                    }
                ) { innerPadding ->
                    FlowerList(
                        flowerList = DataSource.flowerList,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FlowerCard(
    day: Int,
    flower: Flower,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .clip(Shapes.medium),
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_large))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessVeryLow
                    )
                ),
        ) {
            Text(
                text = "Day $day",
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = stringResource(flower.flowerNameId),
                style = MaterialTheme.typography.headlineMedium
            )
            Image(
                painter = painterResource(flower.flowerImageId),
                contentDescription = stringResource(flower.flowerNameId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = if (expanded) dimensionResource(R.dimen.padding_large) else 0.dp)
                    .clip(Shapes.small)
                    .height(230.dp)
            )
            if (expanded) {
                Text(
                    text = stringResource(flower.flowerDescriptionId),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun FlowerList(
    flowerList: List<Flower>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(
            vertical = dimensionResource(R.dimen.padding_medium),
            horizontal = dimensionResource(R.dimen.padding_large)
        )
    ) {
        itemsIndexed(flowerList) { index, flower ->
            FlowerCard(
                day = index + 1,
                flower = flower,
                modifier = Modifier.padding(
                    dimensionResource(R.dimen.padding_medium)
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displaySmall
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
//    Unit3_Pathway3_30DaysAppTheme {
////        FlowerCard(
////            day = 1,
////            flower = Flower(flowerNameId = R.string.hoa_anh_dao_title, flowerDescriptionId = R.string.hoa_anh_dao_description, flowerImageId = R.drawable.hoa_anh_dao)
////        )
//        FlowerList(
//            flowerList = DataSource.flowerList
//        )
//    }
    Unit3_Pathway3_30DaysAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                FlowerTopAppBar()
            }
        ) { innerPadding ->
            FlowerList(
                flowerList = DataSource.flowerList,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModePreview() {
    Unit3_Pathway3_30DaysAppTheme(darkTheme = true) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                FlowerTopAppBar()
            }
        ) { innerPadding ->
            FlowerList(
                flowerList = DataSource.flowerList,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}