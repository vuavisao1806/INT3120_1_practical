package com.example.unit3_pathway2_coursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit3_pathway2_coursesapp.ui.theme.Unit3_Pathway2_CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3_Pathway2_CoursesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseGridApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CourseTopic(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                modifier = Modifier
                    .size(68.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = LocalContext.current.getString(topic.stringResourceId),
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 8.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 8.dp
                        )
                    )
                    Text(
                        text = topic.numberAssociatedCourses.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CourseTopicsColumn(
    topicList: List<Topic>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
    ) {
        items(topicList) { topic ->
            CourseTopic(
                topic,
                modifier = modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun CourseGridApp(
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ) {
        CourseTopicsColumn(
            topicList = DataSource.topics
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CourseAppPreview() {
//    CourseTopic(
//        Topic(R.string.architecture, 58, R.drawable.architecture)
//    )
    CourseTopicsColumn(DataSource.topics)
}
