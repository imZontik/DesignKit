package com.imzontik.designkit.components.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    containerColor: Color = DesignKitTheme.color.surfaceVariant,
    contentColor: Color = DesignKitTheme.color.onSurface,
    shape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp16),
    elevation: Dp = DesignKitTheme.elevations.small,
    borderColor: Color? = null,
    borderWidth: Dp = DesignKitTheme.border.widthThin,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = DesignKitTheme.space.dp16,
        vertical = DesignKitTheme.space.dp12
    ),
    content: @Composable ColumnScope.() -> Unit,
) {
    val border = if (borderColor != null) {
        BorderStroke(borderWidth, borderColor)
    } else {
        null
    }

    if (onClick != null) {
        Surface(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = elevation,
            shadowElevation = elevation,
            border = border,
        ) {
            Column(
                modifier = Modifier.padding(contentPadding),
                content = content
            )
        }
    } else {
        Surface(
            modifier = modifier,
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = elevation,
            shadowElevation = elevation,
            border = border,
        ) {
            Column(
                modifier = Modifier.padding(contentPadding),
                content = content
            )
        }
    }
}

