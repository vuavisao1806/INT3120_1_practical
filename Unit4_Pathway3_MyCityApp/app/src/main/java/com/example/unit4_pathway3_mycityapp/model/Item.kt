package com.example.unit4_pathway3_mycityapp.model

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item(
    val id: Int? = null,
    val icon: Icon? = null,
    @DrawableRes val imageId: Int? = null,
    @StringRes val titleId: Int,
    @StringRes val contentId: Int? = null,
)
