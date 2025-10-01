package com.example.unit4_pathway3_mycityapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.ui.res.stringResource
import com.example.unit4_pathway3_mycityapp.R

object DataSource {
    val category: List<Item> = listOf(
        Item(id = 1, titleId = R.string.cafe),
        Item(id = 2, titleId = R.string.restaurant),
        Item(id = 3, titleId = R.string.shoppingcenter)
    )

    val cafes: List<Item> = listOf(
        Item(titleId = R.string.cafe1_title, contentId = R.string.cafe1_description, imageId = R.drawable.cafe1),
        Item(titleId = R.string.cafe2_title, contentId = R.string.cafe2_description, imageId = R.drawable.cafe2),
        Item(titleId = R.string.cafe3_title, contentId = R.string.cafe3_description, imageId = R.drawable.cafe3),
        Item(titleId = R.string.cafe4_title, contentId = R.string.cafe4_description, imageId = R.drawable.cafe4),
        Item(titleId = R.string.cafe5_title, contentId = R.string.cafe5_description, imageId = R.drawable.cafe5),
    )

    val restaurants: List<Item> = listOf(
        Item(titleId = R.string.restaurant1_title, contentId = R.string.restaurant1_description, imageId = R.drawable.restaurant1),
        Item(titleId = R.string.restaurant2_title, contentId = R.string.restaurant2_description, imageId = R.drawable.restaurant2),
        Item(titleId = R.string.restaurant3_title, contentId = R.string.restaurant3_description, imageId = R.drawable.restaurant3),
        Item(titleId = R.string.restaurant4_title, contentId = R.string.restaurant4_description, imageId = R.drawable.restaurant4),
        Item(titleId = R.string.restaurant5_title, contentId = R.string.restaurant5_description, imageId = R.drawable.restaurant5),
    )

    val shoppingCenters: List<Item> = listOf(
        Item(titleId = R.string.shoppingcenter1_title, contentId = R.string.shoppingcenter1_description, imageId = R.drawable.shoppingcenter1),
        Item(titleId = R.string.shoppingcenter2_title, contentId = R.string.shoppingcenter2_description, imageId = R.drawable.shoppingcenter2),
        Item(titleId = R.string.shoppingcenter3_title, contentId = R.string.shoppingcenter3_description, imageId = R.drawable.shoppingcenter3),
        Item(titleId = R.string.shoppingcenter4_title, contentId = R.string.shoppingcenter4_description, imageId = R.drawable.shoppingcenter4),
        Item(titleId = R.string.shoppingcenter5_title, contentId = R.string.shoppingcenter5_description, imageId = R.drawable.shoppingcenter5),
    )
}