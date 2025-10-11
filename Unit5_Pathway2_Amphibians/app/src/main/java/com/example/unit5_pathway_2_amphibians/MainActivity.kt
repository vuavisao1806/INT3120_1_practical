package com.example.unit5_pathway_2_amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.unit5_pathway_2_amphibians.ui.AmphibiansApp
import com.example.unit5_pathway_2_amphibians.ui.theme.Unit5_Pathway2_AmphibiansTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit5_Pathway2_AmphibiansTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AmphibiansApp()
                }
            }
        }
    }
}