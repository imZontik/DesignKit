package com.imzontik.designkit.components.feedback

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.imzontik.designkit.components.buttons.DesignKitButton
import com.imzontik.designkit.components.buttons.DesignKitTextButton
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String? = null,
    confirmLabel: String? = null,
    dismissLabel: String? = null,
    onConfirm: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
    containerColor: Color = DesignKitTheme.color.surface,
    titleColor: Color = DesignKitTheme.color.onSurface,
    messageColor: Color = DesignKitTheme.color.onSurfaceVariant,
    shape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp24)
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        confirmButton = {
            if (confirmLabel != null && onConfirm != null) {
                DesignKitButton(
                    onClick = onConfirm
                ) {
                    Text(
                        text = confirmLabel,
                        style = DesignKitTheme.typography.labelLarge
                    )
                }
            }
        },
        dismissButton = if (dismissLabel != null && onDismiss != null) {
            {
                DesignKitTextButton(
                    onClick = onDismiss
                ) {
                    Text(
                        text = dismissLabel,
                        style = DesignKitTheme.typography.labelLarge
                    )
                }
            }
        } else {
            null
        },
        title = title?.let {
            {
                Text(
                    text = it,
                    style = DesignKitTheme.typography.titleLarge,
                    color = titleColor
                )
            }
        },
        text = message?.let {
            {
                Text(
                    text = it,
                    style = DesignKitTheme.typography.bodyMedium,
                    color = messageColor
                )
            }
        }
    )
}
