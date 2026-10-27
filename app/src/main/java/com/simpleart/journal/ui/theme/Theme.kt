package com.simpleart.journal.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = TerracottaPrimary,
    onPrimary = TerracottaOnPrimary,
    primaryContainer = TerracottaContainer,
    onPrimaryContainer = TerracottaOnContainer,
    secondary = SageAccent,
    onSecondary = TerracottaOnPrimary,
    secondaryContainer = SageContainer,
    tertiary = OchreAccent,
    background = PaperLight,
    surface = PaperCard,
    surfaceVariant = PaperCardSelected,
    onBackground = InkDark,
    onSurface = InkDark,
    onSurfaceVariant = InkMedium,
    outline = PaperLine
)

private val DarkColorScheme = darkColorScheme(
    primary = TerracottaPrimary,
    onPrimary = TerracottaOnPrimary,
    primaryContainer = TerracottaOnContainer,
    onPrimaryContainer = TerracottaContainer,
    secondary = SageAccent,
    background = PaperDark,
    surface = PaperCardDark,
    onBackground = InkDarkTheme,
    onSurface = InkDarkTheme,
    onSurfaceVariant = InkMediumDarkTheme,
    outline = PaperLine
)

@Composable
fun SimpleArtTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(LocalSpacing provides JournalSpacing()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = JournalShapes,
            content = content
        )
    }
}

@Composable
fun transparentTitleTextFieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    focusedTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
)

@Composable
fun editorTextFieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
    focusedContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
    unfocusedContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
    focusedTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
)
