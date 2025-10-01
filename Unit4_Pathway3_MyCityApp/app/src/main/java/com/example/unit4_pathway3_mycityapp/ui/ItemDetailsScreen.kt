package com.example.unit4_pathway3_mycityapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.unit4_pathway3_mycityapp.R
import com.example.unit4_pathway3_mycityapp.model.MyCityUiState
import com.example.unit4_pathway3_mycityapp.ui.theme.Shapes


@Composable
fun ItemDetailsScreen(
    uiState: MyCityUiState,
    modifier: Modifier = Modifier
) {
    val item = uiState.currentItem
    Card(
        modifier = modifier
            .clip(Shapes.medium)
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_large))
        ) {
            Text(
                text = stringResource(item!!.titleId),
                style = MaterialTheme.typography.headlineMedium
            )
            Image(
                painter = painterResource(item.imageId!!),
                contentDescription = stringResource(item.titleId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.small)
            )
            Text(
                text = stringResource(item.contentId!!),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }
    }
}
