package com.simpleart.journal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.simpleart.journal.ui.theme.LocalSpacing
import com.simpleart.journal.util.DateFormatter
import java.time.LocalDate

@Composable
fun DateNavBar(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val spacing = LocalSpacing.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.large,
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacing.medium,
                    vertical = spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DateNavIconButton(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Previous Day",
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                daysToChange = -1,
            )

            Text(
                text = DateFormatter.shortDate(selectedDate),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            DateNavIconButton(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Next Day",
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                daysToChange = 1,
            )
        }
    }
}

@Composable
private fun DateNavIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String = "",
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    daysToChange: Long = 0L,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    if (daysToChange == 0L) return

    val onClick = if (daysToChange < 0L) {
        { onDateSelected(
            selectedDate.minusDays(-daysToChange)) }
    } else {
        { onDateSelected(
            selectedDate.plusDays(daysToChange)) }
    }

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}
