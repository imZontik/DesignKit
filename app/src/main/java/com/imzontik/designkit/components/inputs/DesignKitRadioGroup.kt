package com.imzontik.designkit.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitRadioGroup(
    items: List<String>,
    selectedItem: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    enabledTextColor: Color = DesignKitTheme.color.onSurface,
    enabledSelectedBorderColor: Color = DesignKitTheme.color.primary,
    enabledUnselectedBorderColor: Color = DesignKitTheme.color.outline,
    enabledDotColor: Color = DesignKitTheme.color.primary,
    disabledTextColor: Color = DesignKitTheme.color.onSurface.copy(alpha = 0.4f),
    disabledSelectedBorderColor: Color = DesignKitTheme.color.primary.copy(alpha = 0.4f),
    disabledUnselectedBorderColor: Color = DesignKitTheme.color.outline.copy(alpha = 0.2f),
    disabledDotColor: Color = DesignKitTheme.color.primary.copy(alpha = 0.4f)

) {
    val textColor = if (enabled) {
        enabledTextColor
    } else {
        disabledTextColor
    }

    val selectedBorderColor = if (enabled) {
        enabledSelectedBorderColor
    } else {
        disabledSelectedBorderColor
    }

    val unselectedBorderColor = if (enabled) {
        enabledUnselectedBorderColor
    } else {
        disabledUnselectedBorderColor
    }

    val dotColor = if (enabled) {
        enabledDotColor
    } else {
        disabledDotColor
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(DesignKitTheme.space.dp12)
    ) {
        items.forEach { item ->
            val isSelected = item == selectedItem

            val animatedBorderColor by animateColorAsState(
                targetValue = if (isSelected) {
                    selectedBorderColor
                } else {
                    unselectedBorderColor
                },
                animationSpec = tween(durationMillis = DesignKitTheme.motion.FAST)
            )

            val animatedDotColor by animateColorAsState(
                targetValue = if (isSelected) {
                    dotColor
                } else {
                    Color.Transparent
                },
                animationSpec = tween(durationMillis = DesignKitTheme.motion.FAST)
            )

            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = true),
                        enabled = enabled,
                        onClick = { onSelect(item) }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(DesignKitTheme.iconSizes.dp24)
                        .clip(CircleShape)
                        .border(
                            width = DesignKitTheme.border.widthThick,
                            color = animatedBorderColor,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(DesignKitTheme.iconSizes.dp12)
                            .clip(CircleShape)
                            .background(animatedDotColor)
                    )
                }

                Spacer(Modifier.width(DesignKitTheme.space.dp12))

                Text(
                    text = item,
                    style = DesignKitTheme.typography.bodyLarge,
                    color = textColor
                )
            }
        }
    }
}
