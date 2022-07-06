package com.raywenderlich.android.runtracker

import android.Manifest
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionManager(
    activity: AppCompatActivity,
    private val locationProvider: LocationProvider,
    private val stepCounter: StepCounter
) {
    private val locationPermissionProvider = activity
        .registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                locationProvider.getUserLocation()
            }
        }

    private val activityRecognitionPermissionProvider = activity
        .registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                stepCounter.setupStepCounter()
            }
        }

    fun requestUserLocation() {
        locationPermissionProvider.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun requestActivityRecognition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activityRecognitionPermissionProvider.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        } else {
            stepCounter.setupStepCounter()
        }
    }
}