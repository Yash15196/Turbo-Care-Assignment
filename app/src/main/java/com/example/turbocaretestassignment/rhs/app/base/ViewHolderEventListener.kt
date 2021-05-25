package com.example.turbocaretestassignment.rhs.app.base

import android.os.Bundle

/**
 * @author : YASH
 * A Listner to be used as an interaction utility between a recyclerview holder and a fragment
 */

interface ViewHolderEventListener<T> {
    /**
     * This method represents the eventtype and data that needs to be passed on an event.
     * Normally events are user interactions such that the onClick etc.
     */
    fun onItemEvent(eventType: Int, position: Int, item: T, bundle: Bundle?)

    /**
     * This method represents the eventtype and data that needs to be passed on an event.
     * Normally events are user interactions such that the onClick etc.
     */
    fun onListEvent(eventType: Int, position: Int, item: List<T>, bundle: Bundle?)
}