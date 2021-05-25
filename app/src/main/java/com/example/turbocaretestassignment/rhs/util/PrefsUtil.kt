package com.example.turbocaretestassignment.rhs.util

import android.content.Context
import android.util.Log
import com.example.turbocaretestassignment.rhs.network.*

class PrefsUtil {

    companion object {

        // Settings to retrieve base url
        var PREFS_BASEURL = "com.cpsl.has.rhs.prefsurl"
        var KEY_LOGIN_PROTOCOL = "protocol"



        /// functions to be used for preferences related to task functionality ends here
        fun getApiBaseUrl(context: Context): String {
            val url = getBaseUrl(context)
            val protocol = getProtocol(context)
            val baseUrl = protocol + PROTCOL_SEPERATOR + url + RSS_BASE_API_PATH
            Log.d(
                "PrefsUtil", "[YASH] getApiBaseUrl()" +
                        " -:- url ==> " + url +
                        " -:- protocol ==> " + protocol +
                        " -:- baseUrl ==> " + baseUrl
            )
            return baseUrl
        }

        fun getBaseUrl(context: Context): String {
//            if(BuildCon)
            val url =  RSS_BASE_URL

            return url ?: ""
        }

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        fun getProtocol(context: Context): String {
            return context.getSharedPreferences(PREFS_BASEURL, Context.MODE_PRIVATE)
                .getString(KEY_LOGIN_PROTOCOL, PROTOCOL_HTTP) ?: ""

        }

    }
}