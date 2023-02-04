package com.example.sampleapp

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CustomShape() : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            // Draw your custom path here
            path = drawTabPath(size = size)
        )
    }
}

fun drawTabPath(size: Size): Path {
    return Path().apply {
        reset()

        // go from (0,0) to (width, 0)
        lineTo(size.width - 10, 0f)

        arcTo(
            rect = Rect(
                Offset(0f, 0f),
                Size(10f, 10f)
            ),
            startAngleDegrees = 270f,
            sweepAngleDegrees = 0f,
            forceMoveTo = false
        )

        // go from (0, height) to (0, 0)
        lineTo(size.width, size.height)
        close()
    }
}