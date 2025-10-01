package com.example.unit4_pathway3_mycityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.unit4_pathway3_mycityapp.ui.theme.Unit4_Pathway3_MyCityAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit4_Pathway3_MyCityAppTheme {
                MyDayApp()
            }
        }
    }
}