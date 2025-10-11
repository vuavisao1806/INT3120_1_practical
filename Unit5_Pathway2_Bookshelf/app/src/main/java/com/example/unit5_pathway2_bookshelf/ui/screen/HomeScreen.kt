package com.example.unit5_pathway2_bookshelf.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unit5_pathway2_bookshelf.R
import com.example.unit5_pathway2_bookshelf.network.Book
import com.example.unit5_pathway2_bookshelf.network.Volume
import com.example.unit5_pathway2_bookshelf.ui.theme.Unit5_Pathway2_BookShelfTheme

@Composable
fun HomeScreen(
    bookShelfUiState: BookShelfUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (bookShelfUiState) {
        is BookShelfUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is BookShelfUiState.Success -> BookGridScreen(bookShelfUiState.data, modifier)
        is BookShelfUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun BookGridScreen(
    data: List<Volume>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        items(
            items = data,
            key = { volume -> volume.id },
        ) {
            volume -> VolumeCard(
                volume = volume,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun VolumeCard(
    volume: Volume,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.size(
            width = 150.dp,
            height = 300.dp
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data(volume.book.imageLinks["thumbnail"])
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_connection_error),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = volume.book.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    Unit5_Pathway2_BookShelfTheme {
        val mockData = List(10) { Volume("$it", Book(
            title = "",
            imageLinks = mutableMapOf()
        )) }
        BookGridScreen(mockData)
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    Unit5_Pathway2_BookShelfTheme {
        ErrorScreen({})
    }
}

