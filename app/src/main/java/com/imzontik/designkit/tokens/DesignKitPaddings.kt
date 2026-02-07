package com.imzontik.designkit.tokens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object DesignKitPaddings {
    val dp8 = 8.dp
    val dp12 = 12.dp
    val dp16 = 16.dp
    val dp20 = 20.dp
    val dp24 = 24.dp

    val screenHorizontal: Dp = dp16
    val screenVertical: Dp = dp16

    val screen: PaddingValues
        get() = PaddingValues(
            horizontal = screenHorizontal,
            vertical = screenVertical
        )

    val screenOnlyHorizontal: PaddingValues
        get() = PaddingValues(
            horizontal = screenHorizontal
        )

    val screenOnlyVertical: PaddingValues
        get() = PaddingValues(
            vertical = screenVertical
        )
}