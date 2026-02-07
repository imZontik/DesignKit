package com.imzontik.designkit.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Shape
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentColor: Color = DesignKitTheme.color.primary,
    borderColor: Color = DesignKitTheme.color.outline,
    borderStroke: Dp = DesignKitTheme.border.widthThin,
    shape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp16),
    contentPadding: PaddingValues = PaddingValues(
        horizontal = DesignKitTheme.space.dp16,
        vertical = DesignKitTheme.space.dp12
    ),
    leading: (@Composable RowScope.() -> Unit)? = null,
    trailing: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        border = BorderStroke(borderStroke, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
            disabledContentColor = contentColor.copy(alpha = 0.3f)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leading != null) {
                leading()
                Spacer(Modifier.width(DesignKitTheme.space.dp8))
            }

            content()

            if (trailing != null) {
                Spacer(Modifier.width(DesignKitTheme.space.dp8))
                trailing()
            }
        }
    }
}