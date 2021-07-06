package com.songstreamer.app.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.*
import com.google.android.material.snackbar.Snackbar
import com.songstreamer.app.R
import com.songstreamer.app.background.LogTrackerWorker
import com.songstreamer.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted)
                Snackbar.make(binding.root,"Permission Granted",Snackbar.LENGTH_SHORT).show()
            else {
                Snackbar.make(binding.root,"Permission Denied",Snackbar.LENGTH_SHORT).show()
            }

        }

        binding.helloWorldText.setOnClickListener {
            //it.visibility = View.GONE
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Snackbar.make(it,"Permission Granted",Snackbar.LENGTH_SHORT).show()
                }
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                ) -> {

                    Snackbar.make(it,"Camera Permission Required!",Snackbar.LENGTH_SHORT).setAction("Request") {
                        permissionRequest.launch(Manifest.permission.CAMERA)
                    }.show()
                }
                else -> {
                    permissionRequest.launch(Manifest.permission.CAMERA)
                }
            }
        }
        //setUpWorker()
    }

    fun String.addPlus() = "$this+"

    private fun setUpWorker() {

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<LogTrackerWorker>()
            .setConstraints(constraints)
            .build()
        //        LogTrackerWorker::class.java,

        WorkManager.getInstance(this@MainActivity)
            .enqueue(workRequest)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.id).observe(this, { workinfo ->
                if(workinfo != null && workinfo.state == WorkInfo.State.SUCCEEDED) {
                    Log.d("Work ","Completed!")
                }
        })
    }
}