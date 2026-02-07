package com.imzontik.designkit.components.inputs

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitSearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    errorText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    showClearButton: Boolean = true,
    textStyle: TextStyle = DesignKitTheme.typography.bodyLarge,
    onSearch: (() -> Unit)? = null,
    onClear: (() -> Unit)? = null,
) {
    DesignKitTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        error = errorText,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        isPasswordField = false,
        textStyle = textStyle,
        leading = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = DesignKitTheme.color.onSurfaceVariant,
                modifier = Modifier.size(DesignKitTheme.iconSizes.dp24)
            )
        },
        trailing = {
            val canClear = showClearButton &&
                    value.isNotEmpty() &&
                    enabled &&
                    !readOnly

            if (canClear) {
                IconButton(
                    onClick = {
                        onValueChange("")
                        onClear?.invoke()
                    },
                    modifier = Modifier.size(DesignKitTheme.iconSizes.dp24)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = DesignKitTheme.color.onSurfaceVariant,
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = if (onSearch != null) {
                ImeAction.Search
            } else {
                ImeAction.Done
            }
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch?.invoke() },
            onDone = { onSearch?.invoke() }
        )
    )
}