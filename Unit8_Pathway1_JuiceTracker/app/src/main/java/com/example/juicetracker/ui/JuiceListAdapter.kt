package com.example.juicetracker.ui

import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.juicetracker.R
import com.example.juicetracker.data.Juice
import com.example.juicetracker.data.JuiceColor
import com.google.accompanist.themeadapter.material3.Mdc3Theme

class JuiceListAdapter(
    private var onEdit: (Juice) -> Unit,
    private var onDelete: (Juice) -> Unit
) : ListAdapter<Juice, JuiceListAdapter.JuiceListViewHolder>(JuiceDiffCallback()) {

    class JuiceListViewHolder(
        private val composeView: ComposeView,
        private val onEdit: (Juice) -> Unit,
        private val onDelete: (Juice) -> Unit
    ) : RecyclerView.ViewHolder(composeView) {

        fun bind(juice: Juice) {
            composeView.setContent {
                ListItem(
                    input = juice,
                    onDelete = onDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEdit(juice)
                        }
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JuiceListViewHolder(
        ComposeView(parent.context),
        onEdit,
        onDelete
    )

    override fun onBindViewHolder(holder: JuiceListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

@Suppress("DEPRECATION")
@Composable
fun ListItem(
    input: Juice,
    onDelete: (Juice) -> Unit,
    modifier: Modifier = Modifier
) {
    Mdc3Theme {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            JuiceIcon(color = input.color)
            JuiceDetails(
                juice = input,
                modifier = Modifier.weight(1f)
            )
            DeleteButton(
                onDelete = {
                    onDelete(input)
                },
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}

@Composable
fun JuiceIcon(
    color: String,
    modifier: Modifier = Modifier
) {
    val colorLabelMap = JuiceColor.entries.associateBy { stringResource(it.label) }
    val selectedColor = colorLabelMap[color]?.let { Color(it.color) }
    val juiceIconContentDescription = stringResource(R.string.juice_color, color)

    Box(
        modifier.semantics {
            contentDescription = juiceIconContentDescription
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_juice_color),
            tint = selectedColor ?: Color.Blue,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            painter = painterResource(R.drawable.ic_juice_clear),
            contentDescription = null
        )
    }
}

@Composable
fun JuiceDetails(juice: Juice, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = juice.name,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(juice.description)
        RatingDisplay(
            rating = juice.rating,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun RatingDisplay(rating: Int, modifier: Modifier) {
    val displayDescription = pluralStringResource(
        id = R.plurals.number_of_stars,
        count = rating
    )
    Row(
        modifier.semantics {
            contentDescription = displayDescription
        }
    ) {
        repeat(rating) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.star),
                contentDescription = null
            )
        }
    }

}

@Composable
fun DeleteButton(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onDelete() },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_delete),
            contentDescription = stringResource(R.string.delete),
        )
    }
}

class JuiceDiffCallback : DiffUtil.ItemCallback<Juice>() {
    override fun areItemsTheSame(oldItem: Juice, newItem: Juice): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Juice, newItem: Juice): Boolean {
        return oldItem == newItem
    }
}

@Preview
@Composable
fun PreviewJuiceDetails() {
    JuiceDetails(Juice(1, "Sweet Beet", "Apple, carrot, beet, and lemon", "Red", 4))
}

@Preview
@Composable
fun PreviewJuiceIcon() {
    JuiceIcon("Yellow")
}

@Preview
@Composable
fun PreviewDeleteIcon() {
    DeleteButton({})
}

@Preview
@Composable
fun PreviewListItem() {
    ListItem(Juice(1, "Sweet Beet", "Apple, carrot, beet, and lemon", "Red", 4), {})
}