package com.example.unit4_pathway3_mycityapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit4_pathway3_mycityapp.R
import com.example.unit4_pathway3_mycityapp.model.DataSource.category
import com.example.unit4_pathway3_mycityapp.model.Item
import com.example.unit4_pathway3_mycityapp.ui.theme.Shapes
import com.example.unit4_pathway3_mycityapp.ui.theme.Unit4_Pathway3_MyCityAppTheme

@Composable
fun DetailItem(
    item: Item,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(Shapes.medium)
            .sizeIn(minHeight = 60.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (item.imageId != null) {
                Image(
                    painter = painterResource(item.imageId),
                    contentDescription = stringResource(item.titleId),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_large)))
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(item.titleId),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}


@Composable
fun SelectItemScreen(
    items: List<Item>,
    onSelectionChanged: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items) { item ->
            DetailItem(
                item = item,
                onClick = { onSelectionChanged(item) },
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Preview
@Composable
fun DetailItemPreview() {
    Unit4_Pathway3_MyCityAppTheme {
//        DetailItem(
//            Item(id = 1, titleId = R.string.cafe),
//            onSelectionChanged = {},
//        )
        SelectItemScreen(
            items = category,
            onSelectionChanged = {},
        )
    }
}
