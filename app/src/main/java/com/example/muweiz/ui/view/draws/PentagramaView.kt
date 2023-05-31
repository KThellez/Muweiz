package com.example.muweiz.ui.view.draws


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class PentagramView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val linePaint: Paint = Paint()

    init {
        linePaint.color = Color.WHITE
        linePaint.strokeWidth = 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = canvas.width
        val height = canvas.height

        val lineSpacing = height / 6 // Espacio entre las líneas del pentagrama

        for (i in 0 until 5) {
            val y = lineSpacing * (i + 1).toFloat()
            canvas.drawLine(0f, y, width.toFloat(), y, linePaint)
        }
    }
}