package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.Blue
import com.example.businesscard.ui.theme.BusinessCardTheme
import com.example.businesscard.ui.theme.Green
import com.example.businesscard.ui.theme.GreenLight
import android.util.Log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.i("My log information", "hello")
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CardBusinessDisplaying(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ChildInformation(icon: ImageVector, content: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Green
        )
        Text(
            text = content,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun AdditionalInformation(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 100.dp,
                bottom = 30.dp
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        ChildInformation(
            icon = Icons.Rounded.Phone,
            content = stringResource(R.string.phone_number),
        )
        ChildInformation(
            icon = Icons.Rounded.Notifications,
            content = stringResource(R.string.youtube),
        )
        ChildInformation(
            icon = Icons.Rounded.Email,
            content = stringResource(R.string.vnu_mail),
        )
    }
}

@Composable
fun MainInformation(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.name),
            fontSize = 45.sp,
            fontWeight = FontWeight(350),
            modifier = Modifier.padding(
                top = 10.dp,
                bottom = 5.dp
            )
        )
        Text(
            text = stringResource(R.string.position),
            fontSize = 20.sp,
            fontWeight = Medium,
            color = Green
        )
    }
}

@Composable
fun CardBusinessDisplaying(modifier: Modifier) {
    Surface(color = GreenLight) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier.padding(top = 180.dp),
                color = Blue) {
                Image(
                    painter = painterResource(R.drawable.android_logo),
                    contentDescription = null,
                    contentScale = FixedScale(0.25F),
                )
            }
            MainInformation(modifier = modifier)
            AdditionalInformation(modifier = modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            ChildInformation(
//                icon = Icons.Rounded.Phone,
//                content = stringResource(R.string.phone_number),
//                modifier = Modifier.padding(innerPadding)
//            )
//            AdditionalInformation(modifier = Modifier.padding(innerPadding))
//            MainInformation(modifier = Modifier.padding(innerPadding))
            CardBusinessDisplaying(modifier = Modifier.padding(innerPadding))
        }
    }
}