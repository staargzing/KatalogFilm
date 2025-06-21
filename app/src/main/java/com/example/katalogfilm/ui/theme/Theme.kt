package com.example.katalogfilm.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightPinkScheme = lightColorScheme(
    primary = Pink40,
    onPrimary = Color.White,
    primaryContainer = Pink80,
    onPrimaryContainer = PinkGrey,
    background = Color(0xFFFFF0F5),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

private val DarkPinkScheme = darkColorScheme(
    primary = Pink80,
    onPrimary = Color.Black,
    primaryContainer = PinkGrey,
    onPrimaryContainer = Color.White,
    background = Color(0xFF2B1B23),
    onBackground = Color.White,
    surface = Color(0xFF3B2C33),
    onSurface = Color.White
)

@Composable
fun KatalogFilmTheme(
    useDarkTheme: Boolean = false, // ubah sesuai kebutuhan
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) DarkPinkScheme else LightPinkScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
