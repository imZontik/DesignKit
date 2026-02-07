package com.imzontik.designkit.components.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitBadge(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = DesignKitTheme.color.primary,
    contentColor: Color = DesignKitTheme.color.onPrimary,
    shape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp12),
    minSize: Dp = DesignKitTheme.iconSizes.dp18,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = DesignKitTheme.space.dp8,
        vertical = DesignKitTheme.space.dp2
    ),
    textStyle: TextStyle = DesignKitTheme.typography.labelSmall,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(containerColor)
            .defaultMinSize(
                minWidth = minSize,
                minHeight = minSize
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            color = contentColor,
            maxLines = 1
        )
    }
}

