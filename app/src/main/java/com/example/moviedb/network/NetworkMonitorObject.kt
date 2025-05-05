package com.example.moviedb.network

import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.MutableStateFlow

object NetworkCallback : ConnectivityManager.NetworkCallback(){
    var isOnline: Boolean = false
        private set

    var isOnlineState: MutableStateFlow<Boolean> = MutableStateFlow(isOnline)

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        isOnline = true
        isOnlineState.value = isOnline
    }
    override fun onLost(network: Network) {
        super.onLost(network)
        isOnline = false
        isOnlineState.value = isOnline
    }
}

