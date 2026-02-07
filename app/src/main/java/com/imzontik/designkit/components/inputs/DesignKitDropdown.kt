package com.imzontik.designkit.components.inputs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.imzontik.designkit.theme.DesignKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignKitDropdown(
    items: List<String>,
    selectedItem: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    error: String? = null,
    enabled: Boolean = true,
    maxMenuHeight: Dp = 320.dp,
    menuBackground: Color = DesignKitTheme.color.surface,
    itemBackground: Color = DesignKitTheme.color.surface,
    selectedBackground: Color = DesignKitTheme.color.primary.copy(alpha = 0.1f),
    textColor: Color = DesignKitTheme.color.onSurface,
    selectedTextColor: Color = DesignKitTheme.color.primary,
    itemShape: Shape = DesignKitTheme.shapes.small,
    menuShape: Shape = DesignKitTheme.shapes.medium,
) {
    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) {
            180f
        } else {
            0f
        },
        animationSpec = tween(durationMillis = DesignKitTheme.motion.NORMAL)
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (enabled) {
                expanded = !expanded
            }
        },
        modifier = modifier
    ) {
        DesignKitTextField(
            value = selectedItem.orEmpty(),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
            label = label,
            placeholder = placeholder,
            error = error,
            enabled = enabled,
            readOnly = true,
            singleLine = true,
            trailing = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = DesignKitTheme.color.onSurfaceVariant,
                    modifier = Modifier.rotate(rotation)
                )
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = menuBackground,
            shape = menuShape
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .heightIn(max = maxMenuHeight)
                    .verticalScroll(scrollState)
                    .padding(vertical = DesignKitTheme.paddings.dp8)
            ) {
                items.forEach { item ->
                    val isSelected = item == selectedItem
                    val currentItemBackground = if (isSelected) {
                        selectedBackground
                    } else {
                        itemBackground
                    }
                    val selectedColor = if (isSelected) {
                        selectedTextColor
                    } else {
                        textColor
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(itemShape)
                            .background(currentItemBackground)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = ripple(bounded = true),
                                onClick = { onSelect(item) }
                            )
                            .padding(
                                horizontal = DesignKitTheme.paddings.dp16,
                                vertical = DesignKitTheme.paddings.dp12
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            style = DesignKitTheme.typography.bodyLarge,
                            color = selectedColor,
                            modifier = Modifier.weight(1f)
                        )

                        if (isSelected) {
                            Spacer(Modifier.width(DesignKitTheme.space.dp8))

                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                tint = selectedTextColor,
                                modifier = Modifier.size(DesignKitTheme.iconSizes.dp18)
                            )
                        }
                    }
                }
            }
        }
    }
}