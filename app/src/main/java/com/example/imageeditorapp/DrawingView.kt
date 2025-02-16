package com.example.imageeditorapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var paint: Paint = Paint()
    private val paths: MutableList<Path> = mutableListOf()
    private val pathPaints: MutableList<Paint> = mutableListOf()
    private var currentPath: Path = Path()

    init {
        paint.color = Color.BLACK
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in paths.indices) {
            canvas.drawPath(paths[i], pathPaints[i])
        }

        canvas.drawPath(currentPath, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {
                paths.add(currentPath)
                pathPaints.add(Paint(paint))
                currentPath = Path()
            }
            else -> return false
        }

        invalidate()
        return true
    }

    fun clear() {
        paths.clear()
        pathPaints.clear()
        currentPath.reset()
        invalidate()
    }

    fun undo() {
        if (paths.isNotEmpty()) {
            paths.removeAt(paths.size - 1)
            pathPaints.removeAt(pathPaints.size - 1)
            invalidate()
        }
    }

    fun setPaintColor(color: Int) {
        paint.color = color
    }

    fun setBrushSize(size: Float) {
        paint.strokeWidth = size
    }
}