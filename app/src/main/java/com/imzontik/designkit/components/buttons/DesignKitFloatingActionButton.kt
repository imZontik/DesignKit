package com.imzontik.designkit.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.graphics.Shape
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = DesignKitTheme.color.primary,
    contentColor: Color = DesignKitTheme.color.onPrimary,
    shape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp16),
    content: @Composable () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = FloatingActionButtonDefaults.elevation()
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}