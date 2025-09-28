package com.example.unit3_pathway3_30daysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Flower(
    @StringRes val flowerNameId: Int,
    @StringRes val flowerDescriptionId: Int,
    @DrawableRes val flowerImageId: Int,
)
