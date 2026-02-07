package com.imzontik.designkit.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    trackWidth: Dp = DesignKitTheme.iconSizes.dp48,
    trackHeight: Dp = DesignKitTheme.iconSizes.dp24,
    thumbSize: Dp = DesignKitTheme.paddings.dp20,
    inset: Dp = DesignKitTheme.space.dp2,
    trackShape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp24),
    enabledTrackCheckedColor: Color = DesignKitTheme.color.primary,
    enabledTrackUncheckedColor: Color = DesignKitTheme.color.outline.copy(alpha = 0.35f),
    enabledBorderColor: Color = DesignKitTheme.color.outline.copy(alpha = 0.35f),
    enabledThumbColor: Color = DesignKitTheme.color.surface,
    enabledTextColor: Color = DesignKitTheme.color.onSurface,
    disabledTrackCheckedColor: Color = DesignKitTheme.color.primary.copy(alpha = 0.4f),
    disabledTrackUncheckedColor: Color = DesignKitTheme.color.outline.copy(alpha = 0.20f),
    disabledBorderColor: Color = DesignKitTheme.color.outline.copy(alpha = 0.20f),
    disabledThumbColor: Color = DesignKitTheme.color.surface.copy(alpha = 0.9f),
    disabledTextColor: Color = DesignKitTheme.color.onSurface.copy(alpha = 0.4f),
) {
    val trackCheckedColor = if (enabled) {
        enabledTrackCheckedColor
    } else {
        disabledTrackCheckedColor
    }

    val trackUncheckedColor = if (enabled) {
        enabledTrackUncheckedColor
    } else {
        disabledTrackUncheckedColor
    }

    val borderColor = if (checked) {
        Color.Transparent
    } else {
        if (enabled) {
            enabledBorderColor
        } else {
            disabledBorderColor
        }
    }

    val thumbColor = if (enabled) {
        enabledThumbColor
    } else {
        disabledThumbColor
    }

    val textColor = if (enabled) {
        enabledTextColor
    } else {
        disabledTextColor
    }

    val animatedTrackColor by animateColorAsState(
        targetValue = if (checked) {
            trackCheckedColor
        } else {
            trackUncheckedColor
        },
        animationSpec = tween(durationMillis = DesignKitTheme.motion.FAST)
    )

    val animatedBorderColor by animateColorAsState(
        targetValue = borderColor,
        animationSpec = tween(durationMillis = DesignKitTheme.motion.FAST)
    )

    val animatedThumbOffset by animateDpAsState(
        targetValue = if (checked) {
            DesignKitTheme.space.dp24
        } else {
            0.dp
        },
        animationSpec = tween(durationMillis = DesignKitTheme.motion.FAST)
    )

    Row(
        modifier = modifier
            .toggleable(
                value = checked,
                enabled = enabled,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true),
                onValueChange = onCheckedChange
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(
                    width = trackWidth,
                    height = trackHeight
                )
                .clip(trackShape)
                .background(animatedTrackColor)
                .border(
                    width = DesignKitTheme.border.widthThin,
                    color = animatedBorderColor,
                    shape = trackShape
                )
                .padding(inset)
        ) {
            Box(
                modifier = Modifier
                    .offset(x = animatedThumbOffset)
                    .size(thumbSize)
                    .shadow(
                        elevation = DesignKitTheme.elevations.small,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .background(thumbColor)
            )
        }

        if (!label.isNullOrBlank()) {
            Spacer(Modifier.width(DesignKitTheme.space.dp12))

            Text(
                text = label,
                style = DesignKitTheme.typography.bodyLarge,
                color = textColor
            )
        }
    }
}

