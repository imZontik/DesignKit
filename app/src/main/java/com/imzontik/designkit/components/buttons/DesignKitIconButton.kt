package com.imzontik.designkit.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentColor: Color = DesignKitTheme.color.onSurfaceVariant,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(DesignKitTheme.iconSizes.dp48),
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = contentColor,
            disabledContentColor = contentColor.copy(alpha = 0.3f)
        )
    ) {
        content()
    }
}
