package com.example.unit5_pathway2_bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.unit5_pathway2_bookshelf.ui.BookShelfApp
import com.example.unit5_pathway2_bookshelf.ui.theme.Unit5_Pathway2_BookShelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit5_Pathway2_BookShelfTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BookShelfApp()
                }
            }
        }
    }
}