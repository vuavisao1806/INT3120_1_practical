package com.example.unit3_pathway2_coursesapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val numberAssociatedCourses: Int,
    @DrawableRes val imageResourceId: Int
)
