package com.example.unit2_pathway2_lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit2_pathway2_lemonade.ui.theme.LightGreenOff
import com.example.unit2_pathway2_lemonade.ui.theme.LightGreenOn
import com.example.unit2_pathway2_lemonade.ui.theme.Unit2_Pathway2_LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit2_Pathway2_LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeDisplaying(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ActionDescription(modifier: Modifier = Modifier) {
    var step by remember { mutableIntStateOf(0) }
    var lemonSqueezerSteps by remember { mutableIntStateOf(0) }

    val imageResource = when(step) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val contentActionResource = when(step) {
        0 -> R.string.lemon_tree_action_description
        1 -> R.string.lemon_action_description
        2 -> R.string.lemonade_action_description
        else -> R.string.empty_glass_action_description
    }
    val contentDescriptionResource = when(step) {
        0 -> R.string.lemon_tree_content_description
        1 -> R.string.lemon_content_description
        2 -> R.string.lemonade_content_description
        else -> R.string.empty_glass_content_description
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (step == 1) {
                    --lemonSqueezerSteps
                    if (lemonSqueezerSteps == 0) {
                        ++step
                    }
                } else {
                    step = (step + 1) % 4
                    if (step == 1) {
                        lemonSqueezerSteps = (2..4).random()
                    }
                }
            },
            modifier = Modifier.size(size = 200.dp),
            shape = RoundedCornerShape(corner = CornerSize(60.dp)),
            colors = ButtonColors(
                containerColor = LightGreenOn,
                contentColor = Color.Gray,
                disabledContainerColor = LightGreenOff,
                disabledContentColor = Color.DarkGray
            )
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(contentDescriptionResource)
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = stringResource(contentActionResource),
            fontSize =  18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeDisplaying(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = Color.Yellow,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.lemonade),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
            }
        }
        ActionDescription()
    }
}