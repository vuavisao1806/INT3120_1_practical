package com.example.unit6_pathway3_flightsearch.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
//    small = RoundedCornerShape(4.dp),
//    medium = RoundedCornerShape(16.dp),
    extraSmall = CutCornerShape(topEnd = 8.dp, bottomStart = 8.dp),
    small = CutCornerShape(topEnd = 8.dp, bottomStart = 8.dp),
    medium = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
)
