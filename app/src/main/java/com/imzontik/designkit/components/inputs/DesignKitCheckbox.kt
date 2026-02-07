package com.imzontik.designkit.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
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
fun DesignKitCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
) {
    val shape = RoundedCornerShape(DesignKitTheme.radius.dp8)

    val backgroundColor = if (enabled) {
        DesignKitTheme.color.primary
    } else {
        DesignKitTheme.color.primary.copy(alpha = 0.4f)
    }

    val borderColor = when {
        !enabled -> DesignKitTheme.color.outline.copy(alpha = 0.20f)
        checked -> DesignKitTheme.color.primary
        else -> DesignKitTheme.color.outline
    }

    val checkmarkColor = DesignKitTheme.color.onPrimary

    val textColor = if (enabled) {
        DesignKitTheme.color.onSurface
    } else {
        DesignKitTheme.color.onSurface.copy(alpha = 0.4f)
    }

    val animatedBackground by animateColorAsState(
        targetValue = if (checked) {
            backgroundColor
        } else {
            Color.Transparent
        },
        animationSpec = tween(durationMillis = DesignKitTheme.motion.FAST)
    )

    val animatedBorderColor by animateColorAsState(
        targetValue = borderColor,
        animationSpec = tween(durationMillis = DesignKitTheme.motion.FAST)
    )

    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true),
                enabled = enabled,
                onClick = { onCheckedChange(!checked) }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(DesignKitTheme.iconSizes.dp24)
                .clip(shape)
                .background(animatedBackground)
                .border(
                    width = DesignKitTheme.border.widthThick,
                    color = animatedBorderColor,
                    shape = shape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = checkmarkColor,
                    modifier = Modifier.size(DesignKitTheme.iconSizes.dp18)
                )
            }
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
