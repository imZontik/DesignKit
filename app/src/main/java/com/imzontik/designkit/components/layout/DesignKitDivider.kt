package com.imzontik.designkit.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitDivider(
    modifier: Modifier = Modifier,
    color: Color = DesignKitTheme.color.outline.copy(alpha = 0.35f),
    strokeWidth: Dp = DesignKitTheme.border.widthThin,
    startIndent: Dp = 0.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = startIndent)
            .height(strokeWidth)
            .background(color)
    )
}

