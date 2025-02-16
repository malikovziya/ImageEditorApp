package com.example.imageeditorapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var canvasView: DrawingView
    private var paintColor: Int = Color.BLACK
    private var brushSize: Float = 10f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        canvasView = findViewById(R.id.canvasView)

        val colorRed = findViewById<ImageButton>(R.id.colorRed)
        val colorGreen = findViewById<ImageButton>(R.id.colorGreen)
        val colorBlue = findViewById<ImageButton>(R.id.colorBlue)
        val colorYellow = findViewById<ImageButton>(R.id.colorYellow)
        val colorBlack = findViewById<ImageButton>(R.id.colorBlack)
        val colorWhite = findViewById<ImageButton>(R.id.colorWhite)
        val colorPurple = findViewById<ImageButton>(R.id.colorPurple)

        val brushSizeSeekBar = findViewById<SeekBar>(R.id.brushSizeSeekBar)

        brushSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                brushSize = (progress + 1).toFloat()
                canvasView.setBrushSize(brushSize)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        colorRed.setOnClickListener { setPaintColor(Color.RED) }
        colorGreen.setOnClickListener { setPaintColor(Color.GREEN) }
        colorBlue.setOnClickListener { setPaintColor(Color.BLUE) }
        colorYellow.setOnClickListener { setPaintColor(Color.YELLOW) }
        colorBlack.setOnClickListener { setPaintColor(Color.BLACK) }
        colorWhite.setOnClickListener { setPaintColor(Color.WHITE) }
        colorPurple.setOnClickListener { setPaintColor(Color.parseColor("#FF6200EE")) }

        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            canvasView.clear()
        }

        val undoButton = findViewById<Button>(R.id.undoButton)
        undoButton.setOnClickListener {
            canvasView.undo()
        }

        canvasView.setPaintColor(paintColor)
        canvasView.setBrushSize(brushSize)
    }

    private fun setPaintColor(color: Int) {
        paintColor = color
        canvasView.setPaintColor(paintColor)
    }
}