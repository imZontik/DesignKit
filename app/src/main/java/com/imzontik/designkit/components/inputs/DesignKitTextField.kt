package com.imzontik.designkit.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    error: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    isPasswordField: Boolean = false,
    leading: (@Composable (() -> Unit))? = null,
    trailing: (@Composable (() -> Unit))? = null,
    textStyle: TextStyle = DesignKitTheme.typography.bodyLarge,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val isError = !error.isNullOrBlank()
    val interactionSource = remember { MutableInteractionSource() }

    val shape = RoundedCornerShape(DesignKitTheme.radius.dp16)
    val borderColor = when {
        !enabled -> DesignKitTheme.color.outline.copy(alpha = 0.20f)
        isError -> DesignKitTheme.color.error
        else -> DesignKitTheme.color.outline.copy(alpha = 0.35f)
    }

    val visualTransformation = if (isPasswordField) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    Column(modifier = modifier) {
        if (!label.isNullOrBlank()) {
            Text(
                text = label,
                style = DesignKitTheme.typography.labelMedium,
                color = if (isError) {
                    DesignKitTheme.color.error
                } else {
                    DesignKitTheme.color.onSurfaceVariant
                }
            )
            Spacer(Modifier.height(DesignKitTheme.space.dp8))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(DesignKitTheme.color.surfaceVariant)
                .border(
                    width = DesignKitTheme.border.widthThin,
                    color = borderColor,
                    shape = shape
                )
                .padding(
                    horizontal = DesignKitTheme.space.dp16,
                    vertical = DesignKitTheme.space.dp12
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leading != null) {
                leading()
                Spacer(Modifier.width(DesignKitTheme.space.dp8))
            }

            Box(Modifier.weight(1f)) {
                val textFieldTextStyle = if (enabled) {
                    DesignKitTheme.color.onSurface
                } else {
                    DesignKitTheme.color.onSurface.copy(alpha = 0.4f)
                }

                if (value.isEmpty() && !placeholder.isNullOrBlank()) {
                    Text(
                        text = placeholder,
                        style = textStyle,
                        color = DesignKitTheme.color.onSurfaceVariant.copy(alpha = 0.75f),
                    )
                }

                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    textStyle = textStyle.copy(
                        color = textFieldTextStyle
                    ),
                    cursorBrush = SolidColor(DesignKitTheme.color.primary),
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (trailing != null) {
                Spacer(Modifier.width(DesignKitTheme.space.dp8))
                trailing()
            }
        }

        error?.let {
            Spacer(Modifier.height(DesignKitTheme.space.dp8))
            Text(
                text = it,
                style = DesignKitTheme.typography.labelMedium,
                color = DesignKitTheme.color.error
            )
        }
    }
}