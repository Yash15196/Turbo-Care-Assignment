package com.cpsl.has.rhs.app.base.custom

/**
 * @author yash gupta
 * This interface is used by @see TasksViewModel to save image in the @see HandwritingView
 */
interface HandwritingImageSaver {
    /**
     * Function to save the drawn image
     * @param imagePath : Path where image is to be saved
     */
    fun onSaveRequest(imagePath: String): Boolean
}