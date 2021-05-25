package com.example.turbocaretestassignment.rhs.app.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.ContextThemeWrapper

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider import com.example.turbocaretestassignment.rhs.util.connectivity.ConnectivityReceiver
import com.example.turbocaretestassignment.rhs.util.connectivity.ConnectivityReceiverListener
import com.example.turbocaretestassignment.R

import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @author yash gupta
 *  Base Fragment class.
 *  All the fragments in the application should extend this class.
 *  It expects a type that extends @see AndroidViewModel
 *  Each fragment class should use its AndroidViewModel
 */
abstract class BaseFragment<T : AndroidViewModel> : DaggerFragment() {
    // This will be initialized in initViewModel() method
    lateinit var viewModel: T

    // common dilaog to display errors
    private var errorDialog: AlertDialog? = null

    // broadcast receiver to check network connectivity
    var connectivityReceiver: ConnectivityReceiver = ConnectivityReceiver()
    var progressDialog :Dialog?=null
    // Injects viewmodel factory dependency
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * overridden method so that viewmodel can be initialized
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel=initViewModel()
    }

    /**
     * method is overridden so that swipe refresh keyboardFocusOutView setup can be done as soon as activity is created
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    /**
     * method is overridden to register broadcast receiver so that network state can be listened
     */
    @Suppress("DEPRECATION")
    override fun onResume() {
        super.onResume()
        // register receiver here
        activity?.registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        // setup listenerEvent
        val listener: ConnectivityReceiverListener = object : ConnectivityReceiverListener {
            override fun onNetworkConnectionChanged(isConnected: Boolean) {
                showMessage(isConnected)
            }
        }
        connectivityReceiver.setListener(listener)
    }

open fun showHideProgressBar(show:Boolean){
    if(show) {
        if (progressDialog == null || progressDialog?.isShowing == false) {
            activity?.let {
                progressDialog = Dialog(it)
                progressDialog?.setContentView(R.layout.dialog_progress)
                progressDialog?.setCancelable(false)
            }
        }
        progressDialog?.show()
    }
        else{
            if(progressDialog?.isShowing==true){
                progressDialog?.dismiss()
            }
        progressDialog=null
        }
    }


    private fun showMessage(isConnected: Boolean) {
        activity?.let {
            val dialogBuilder = AlertDialog.Builder(ContextThemeWrapper(activity, R.style.AlertDialogCustom))
            if (!isConnected) {
                val messageToUser = getString(R.string.error_no_connectivity_dialog)
                // set message of alert dialog
                dialogBuilder.setMessage(messageToUser)
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton(getString(R.string.dlg_btn_ok)) { dialog, id ->
                        dialog.dismiss()
                    }.show()
            } else {
                val messageToUser = getString(R.string.msg_connected_to_internet)
//                Toast.makeText(it, messageToUser, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // unregister receiver from the fragments that are paused
        connectivityReceiver.removeListener()
        activity?.unregisterReceiver(connectivityReceiver)
    }

    /**
     * Method should be overridden by subclasses to initialize ViewModel
     */
    abstract fun initViewModel(): T



}