package com.imzontik.designkit.components.effects

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.imzontik.designkit.theme.DesignKitTheme

@Composable
fun DesignKitShimmerEffect(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(DesignKitTheme.radius.dp12),
    baseColor: Color = DesignKitTheme.color.surfaceVariant,
    highlightColor: Color = DesignKitTheme.color.onSurface.copy(alpha = 0.1f),
    durationMillis: Int = DesignKitTheme.motion.SLOW,
) {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = modifier
            .clip(shape)
            .drawWithCache {
                val width = size.width
                val height = size.height
                val highlightWidth = width * 0.65f
                val x = (width + 4f * highlightWidth) * progress - 2f * highlightWidth

                val brush = Brush.linearGradient(
                    colors = listOf(
                        baseColor,
                        highlightColor,
                        baseColor
                    ),
                    start = Offset(x - highlightWidth, 0f),
                    end = Offset(x + highlightWidth, height)
                )

                onDrawBehind {
                    drawRect(brush = brush)
                }
            }
    )
}

