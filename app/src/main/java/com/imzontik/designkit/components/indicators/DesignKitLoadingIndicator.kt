package com.imzontik.designkit.components.indicators

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitLoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = DesignKitTheme.iconSizes.dp24,
    strokeWidth: Dp = DesignKitTheme.border.widthThick,
    color: Color = DesignKitTheme.color.primary,
) {
    CircularProgressIndicator(
        modifier = modifier.size(size),
        color = color,
        strokeWidth = strokeWidth
    )
}

