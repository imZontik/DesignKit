package com.imzontik.designkit.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Shape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = DesignKitTheme.color.primary,
        contentColor = DesignKitTheme.color.onPrimary,
        disabledContainerColor = DesignKitTheme.color.onSurface.copy(alpha = 0.2f),
        disabledContentColor = DesignKitTheme.color.onSurface.copy(alpha = 0.4f)
    ),
    shape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp16),
    contentPadding: PaddingValues = PaddingValues(
        horizontal = DesignKitTheme.space.dp16,
        vertical = DesignKitTheme.space.dp12
    ),
    leading: (@Composable RowScope.() -> Unit)? = null,
    trailing: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        colors = colors
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leading != null) {
                leading()
                Spacer(modifier = Modifier.width(DesignKitTheme.space.dp4))
            }

            content()

            if (trailing != null) {
                Spacer(modifier = Modifier.width(DesignKitTheme.space.dp4))
                trailing()
            }
        }
    }
}