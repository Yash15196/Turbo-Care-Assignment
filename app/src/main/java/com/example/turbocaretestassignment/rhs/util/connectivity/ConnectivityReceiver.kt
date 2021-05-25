package com.example.turbocaretestassignment.rhs.util.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityReceiver() : BroadcastReceiver() {

    private var connectivityReceiverListener: ConnectivityReceiverListener? = null

    fun removeListener() {
        connectivityReceiverListener = null
    }

    fun setListener(listener: ConnectivityReceiverListener) {
        connectivityReceiverListener = listener
    }
    override fun onReceive(context: Context, arg1: Intent) {
        connectivityReceiverListener?.onNetworkConnectionChanged(isConnectedOrConnecting(context))
    }

    private fun isConnectedOrConnecting(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}

interface ConnectivityReceiverListener {
    fun onNetworkConnectionChanged(isConnected: Boolean)
}