package com.cpsl.has.rhs.app.base.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewParent
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

/**
 * @author yash.gupta
 * This classs is used to create a view to capture handwriting e.g. signature and save as an image
 */
class HandwritingView(context: Context, attrs: AttributeSet?, val handwritingListener: HandwritingListener) :
    View(context, attrs), HandwritingImageSaver {
    val TAG = "HandwritingView"
    private val STROKE_WIDTH = 5f // predefined stroke width for the view
    private val HALF_STROKE_WIDTH = STROKE_WIDTH / 2  // predefined half of the stroke width for the view
    private val paint = Paint() // paint object to draw the view
    private val path = Path() // path instance
    private var lastTouchX: Float = 0.toFloat() // to track last touched x direction
    private var lastTouchY: Float = 0.toFloat() // to track last touched y direction
    private val dirtyRect = RectF() // instance of RectF to be used while drawing rectangle for this view

    init {
        paint.isAntiAlias = true // set anti alias to smooth out edges of view
        paint.color = Color.BLACK // set the paint color as black for the image to be captured
        paint.style = Paint.Style.STROKE // set the style as stroke
        paint.strokeJoin = Paint.Join.ROUND // set the join as round
        paint.strokeWidth = STROKE_WIDTH // set the stroke width
    }

    /**
     * Function to save the drawn image
     * @param imagePath : Path where image is to be saved
     */
    override fun onSaveRequest(imagePath: String): Boolean {
        val parent: ViewParent? = getParent() // get the parent
        var bitmap: Bitmap? = null // bitmap to be saved
        parent?.let { it1 ->
            val parentView: View = it1 as View
            bitmap = Bitmap.createBitmap(parentView.width, parentView.height, Bitmap.Config.RGB_565)
        }

        val canvas = bitmap?.let { Canvas(it) }
        // Output the file
        try {
            val mFileOutStream = FileOutputStream(imagePath)
            draw(canvas);
            // Convert the output file to Image such as .png
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 90, mFileOutStream)
            mFileOutStream.flush()
            mFileOutStream.close()
        } catch (fne: FileNotFoundException) {
            return false
        } catch (ioe: IOException) {
            return false
        }
        return true
    }

    /**
     * Function to clear this view and reset
     */
    fun clear() {
        path.reset()
        invalidate()
    }

    /**
     * Overridden method to draw the view
     */
    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    /**
     * Overridden method to handle touch events and draw the graphics as user touches on the screen
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val eventX = event.x
        val eventY = event.y
        handwritingListener.onHandwritingEvent()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                parent?.requestDisallowInterceptTouchEvent(true)
                path.moveTo(eventX, eventY)
                lastTouchX = eventX
                lastTouchY = eventY
                return true
            }
            MotionEvent.ACTION_MOVE,
            MotionEvent.ACTION_UP -> {
                resetDirtyRect(eventX, eventY)
                val historySize = event.historySize
                for (i in 0..historySize - 1) {
                    val historicalX = event.getHistoricalX(i)
                    val historicalY = event.getHistoricalY(i)
                    expandDirtyRect(historicalX, historicalY)
                    path.lineTo(historicalX, historicalY)
                }
                path.lineTo(eventX, eventY)
                parent?.requestDisallowInterceptTouchEvent(true)
            }
            else -> {
                return false
            }
        }

        invalidate(
            (dirtyRect.left - HALF_STROKE_WIDTH).toInt(),
            (dirtyRect.top - HALF_STROKE_WIDTH).toInt(),
            (dirtyRect.right + HALF_STROKE_WIDTH).toInt(),
            (dirtyRect.bottom + HALF_STROKE_WIDTH).toInt()
        )

        lastTouchX = eventX
        lastTouchY = eventY

        return true
    }

    private fun expandDirtyRect(historicalX: Float, historicalY: Float) {
        if (historicalX < dirtyRect.left) {
            dirtyRect.left = historicalX
        } else if (historicalX > dirtyRect.right) {
            dirtyRect.right = historicalX
        }

        if (historicalY < dirtyRect.top) {
            dirtyRect.top = historicalY
        } else if (historicalY > dirtyRect.bottom) {
            dirtyRect.bottom = historicalY
        }
    }

    private fun resetDirtyRect(eventX: Float, eventY: Float) {
        dirtyRect.left = Math.min(lastTouchX, eventX)
        dirtyRect.right = Math.max(lastTouchX, eventX)
        dirtyRect.top = Math.min(lastTouchY, eventY)
        dirtyRect.bottom = Math.max(lastTouchY, eventY)
    }
    // Extension function to show toast message
}

interface HandwritingListener {
    fun onHandwritingEvent()
}