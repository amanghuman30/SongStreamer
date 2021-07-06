package com.songstreamer.app.background

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.lang.Exception

class LogTrackerWorker(val ctx : Context, val workerParameters: WorkerParameters) : CoroutineWorker(ctx, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            sendLogsToServer()
            Result.success()
        } catch (e : Exception) {
            if(runAttemptCount < 3)
                Result.retry()
            else
                Result.failure()
        }
    }

    private fun sendLogsToServer() {
        Log.d("test","${Thread.currentThread().name}")
        for (i in 0..10) {
            //CoroutineScope(Dispatchers.Main).launch {
                Log.d("test","Log item number : $i")
            //}
            Thread.sleep(1000)
        }
    }

}