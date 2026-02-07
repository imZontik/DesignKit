package com.imzontik.designkit.components.feedback

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Shape
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.imzontik.designkit.components.buttons.DesignKitIconButton
import com.imzontik.designkit.components.buttons.DesignKitTextButton
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitSnackBar(
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
    containerColor: Color = DesignKitTheme.color.onSurface,
    contentColor: Color = DesignKitTheme.color.surface,
    shape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp16)
) {
    Snackbar(
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        action = if (actionLabel != null && onAction != null) {
            {
                DesignKitTextButton(
                    onClick = onAction,
                    contentColor = DesignKitTheme.color.primary
                ) {
                    Text(
                        text = actionLabel,
                        style = DesignKitTheme.typography.labelLarge
                    )
                }
            }
        } else {
            null
        },
        dismissAction = if (onDismiss != null) {
            {
                DesignKitIconButton(
                    onClick = onDismiss,
                    contentColor = contentColor
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier.size(DesignKitTheme.iconSizes.dp18)
                    )
                }
            }
        } else {
            null
        }
    ) {
        Row(
            modifier = Modifier.padding(vertical = DesignKitTheme.paddings.dp8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                style = DesignKitTheme.typography.bodyMedium,
                color = contentColor
            )
        }
    }
}
