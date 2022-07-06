package com.raywenderlich.android.runtracker

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionManager(
    activity: AppCompatActivity,
    private val locationProvider: LocationProvider
) {
    private val locationPermissionProvider = activity
        .registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                locationProvider.getUserLocation()
            }
        }

    fun requestUserLocation() {
        locationPermissionProvider.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}